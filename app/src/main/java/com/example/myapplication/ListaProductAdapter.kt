package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView

class ListaProductAdapter(private val listaProductos: ArrayList<Product>) : RecyclerView.Adapter<ListaProductAdapter.PersonaViewHolder>() {

    // Método invocado cuando se necesita crear una nueva instancia del ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        // Inflar la vista del elemento de la lista a partir del layout 'lista_item_datos'
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return PersonaViewHolder(view)
    }

    // Método invocado para establecer los datos de una persona en una posición específica del RecyclerView
    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        // Obtener la persona en la posición 'position' de la lista y establecer sus datos en las vistas correspondientes del ViewHolder
        val product = listaProductos[position]
        holder.viewMarca.text = product.getMarca()
        holder.viewdescripcion.text = product.getDescripcion()
        holder.viewValor.text = product.getValor()
        holder.imageView.setImageResource(product.getImagenResId())
    }

    // Método invocado para obtener la cantidad de elementos en la lista
    override fun getItemCount(): Int {
        return listaProductos.size
    }

    // Definición de la clase interna PersonaViewHolder, que extiende RecyclerView.ViewHolder
    inner class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Referencias a las vistas en el layout 'lista_item_datos'
        val viewMarca: TextView = itemView.findViewById(R.id.viewMarca)
        val viewdescripcion: TextView = itemView.findViewById(R.id.viewDescripcion)
        val viewValor: TextView = itemView.findViewById(R.id.viewValor)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}
