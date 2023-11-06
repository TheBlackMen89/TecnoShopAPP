package com.example.myapplication

class Product(private var marca: String, private var descripcion: String, private var valor: String, private var imagenResId: Int) {

    fun getMarca(): String {
        return marca
    }

    fun setmarca(marca: String) {
        this.marca = marca
    }

    fun getDescripcion(): String {
        return descripcion
    }

    fun setDescripcion(descripcion: String) {
        this.descripcion = descripcion
    }

    fun getValor(): String {
        return valor
    }

    fun setValor(valor: String) {
        this.valor = valor
    }
    fun getImagenResId(): Int {
        return imagenResId
    }

    fun setImagenResId(imagenResId: Int) {
        this.imagenResId = imagenResId
    }
}
