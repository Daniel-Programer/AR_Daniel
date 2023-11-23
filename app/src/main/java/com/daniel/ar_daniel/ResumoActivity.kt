package com.daniel.ar_daniel

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daniel.ar_daniel.databinding.ActivityOpcoesBinding

class ResumoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpcoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpcoesBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_resumo)


//Criando um intent que recupera os valores da activity de origem

        val i = intent

        /*O trecho: ?.toDoubleOrNull() ?: 0.0, converte as strings para números double.
        Isso tentará converter a string para um double, e se não conseguir (por exemplo,
        se a string não for um número válido), usará 0.0 como valor padrão.
         */
        val valorSapato = intent.getDoubleExtra("valorSapato", 0.0)
        val qtdSapato = intent.getStringExtra("qtdSapato")?.toDoubleOrNull() ?: 0.0
        val valorCamisa = intent.getDoubleExtra("valorCamisa", 0.0)
        val qtdCamisa = intent.getStringExtra("qtdCamisa")?.toDoubleOrNull() ?: 0.0
        val valorBermuda = intent.getDoubleExtra("valorBermuda", 0.0)
        val qtdBermuda = intent.getStringExtra("qtdBermuda")?.toDoubleOrNull() ?: 0.0

        /*Neste exemplo, estou calculando o total para cada item (sapato, camisa, bermuda)
        multiplicando a quantidade pelo valor. Então, eu somo todos esses totais para obter o totalGeral.
        Finalmente, eu adiciono o totalGeral à string que é exibida no TextView.
         */
        var totalSapato = valorSapato * qtdSapato
        var totalCamisa = valorCamisa * qtdCamisa
        var totalBermuda = valorBermuda * qtdBermuda

        val totalGeral = totalSapato + totalCamisa + totalBermuda

        /*Buscando o elemento pelo seu id e pasando todas as informaçoes para dentro do textViewResumo
        no campo texto
         */
        val textViewResumo = findViewById<TextView>(R.id.textViewResumo)
        textViewResumo.text = """
            Sapato: Quantidade = $qtdSapato, Valor = $valorSapato 
            Camisa: Quantidade = $qtdCamisa, Valor = $valorCamisa
            Bermuda: Quantidade = $qtdBermuda, Valor = $valorBermuda
            
            A soma dos seus pedidos é: $totalGeral
        """.trimIndent()
    }
}