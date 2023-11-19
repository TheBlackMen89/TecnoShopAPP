package com.example.myapplication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

public class FirebaseHelper {

    private DatabaseReference databaseReference;
    private FirebaseAuth auth;

    public FirebaseHelper() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.auth = FirebaseAuth.getInstance();
    }

    // Métodos para la autenticación
    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    public void signIn(String email, String password, final Callback<Boolean, String> callback) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(true);
                    } else {
                        callback.onFailure(task.getException() != null ? task.getException().getMessage() : "Error desconocido");
                    }
                });
    }

    public void signOut() {
        auth.signOut();
    }

    // Métodos para la base de datos en tiempo real de Firebase
    public void addUserToDatabase(String userId, String username, String email) {
        User user = new User(username, email);
        databaseReference.child("users").child(userId).setValue(user);
    }

    public void getUserData(String userId, final Callback<User, Void> callback) {
        databaseReference.child("users").child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        callback.onSuccess(user);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.onFailure(null);
                    }
                });
    }

    // Clase de modelo para el usuario
    public static class User {
        public String username;
        public String email;

        public User() {
            // Constructor vacío requerido para Firebase
        }

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }

    // Interfaz de devolución de llamada para manejar el resultado asíncrono
    public interface Callback<T, E> {
        void onSuccess(T result);
        void onFailure(E error);
    }
}