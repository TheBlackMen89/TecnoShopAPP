package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductList : AppCompatActivity() {


    private lateinit var listaProductos: RecyclerView
    private var listaArrayProductos: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        listaProductos = findViewById(R.id.listadoProductos)
        listaProductos.layoutManager = LinearLayoutManager(this)

        listaArrayProductos.add(Product("Computador Portátil HP 15,6", "AMD R5- RAM 8GB", "$2.049.000 Hoy", R.drawable.pc1))
        listaArrayProductos.add(Product("Computador Portátil LENOVO 15,6", "Intel Core i5 - RAM 8GB", "$2.119.000 Hoy", R.drawable.pc2))
        listaArrayProductos.add(Product("Computador Portátil ASUS 15,6", "Intel Core i3 - RAM 8GB", "$1.699.000 Hoy", R.drawable.pc3))
        listaArrayProductos.add(Product("Computador Portátil Gamer Victus HP 15,6", "Intel Core I5- RAM 16GB", "$4.149.000 Hoy", R.drawable.pc4))
        listaArrayProductos.add(Product("Computador Portátil ASUS Vivobook Go 15.6", "Intel Core i3 – RAM 8 GB", "$1.719.000 Hoy", R.drawable.pc5))
        listaArrayProductos.add(Product("Computador portátil Gamer ACER NITRO 15.6", "Intel Core i5 - RAM 8GB", "$3.849.000 Hoy", R.drawable.pc6))
        listaArrayProductos.add(Product("Computador Portátil Gamer ASUS TUF 15,6", "Intel Core i5 - RAM 8GB", "$3.599.000 Hoy", R.drawable.pc7))
        listaArrayProductos.add(Product("MacBook Air de 13", "MGN63LA/A Chip M1 RAM 8GB", "$4.999.000 Hoy", R.drawable.pc8))
        listaArrayProductos.add(Product("Computador portátil ACER ASPIRE 3", " Intel Core i3 - RAM 8GB", "$1.699.000 Hoy", R.drawable.pc9))
        listaArrayProductos.add(Product("Computador Portátil Gamer Victus HP 15.6", "AMD Ryzen 5 - RAM 8GB", "$3.399.000 Hoy", R.drawable.pc10))
        listaArrayProductos.add(Product("Computador Portátil Gamer ROG Strix G", "G513RC - AMD Ryzen 7", "$5.799.000 Hoy", R.drawable.pc11))
        listaArrayProductos.add(Product("MacBook Air 13", "MLY13E/A - Chip M2 - RAM 8GB", "$7.189.000 Hoy", R.drawable.pc12))
        listaArrayProductos.add(Product("Computador Portátil Gamer ASUS TUF Dash", "Intel Core i7 - RAM 16GB", "$6.799.000 Hoy", R.drawable.pc13))
        listaArrayProductos.add(Product("Computador Portátil Gamer ROG Strix Scar", "G733CX - Intel Core i9 - RAM 32GB", "$19.999.000 Hoy", R.drawable.pc14))
        listaArrayProductos.add(Product("Computador Portátil Gamer ROG Strix Scar 18", "Intel Core i9 - RAM 64GB", "$18.499.000 Hoy", R.drawable.pc15))
        listaArrayProductos.add(Product("Computador portátil ACER NITRO 15", "Intel Core i5 - RAM 16GB", "$5.999.000 Hoy", R.drawable.pc16))
        listaArrayProductos.add(Product("Computador Portátil Gamer MSI GF63", "Intel Ci5 - RAM 16GB", "$4.999.000 Hoy", R.drawable.pc17))
        listaArrayProductos.add(Product("Computador portátil Gamer PREDATOR 16", "Intel Core i7 - RAM 16GB", "$8.999.000 Hoy", R.drawable.pc18))
        listaArrayProductos.add(Product("Computador Portátil Gamer ROG Strix 18", "Intel Core i9 - RAM 16GB", "$12.999.000 Hoy", R.drawable.pc19))


        val adapter = ListaProductAdapter(listaArrayProductos)
        listaProductos.adapter = adapter

    }
}