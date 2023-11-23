package com.daniel.ar_daniel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.daniel.ar_daniel.databinding.ActivityOpcoesBinding

class OpcoesActivity : AppCompatActivity() {


    private lateinit var binding: ActivityOpcoesBinding

    private lateinit var checkBoxSapato: CheckBox
    private lateinit var checkBoxCamisa: CheckBox
    private lateinit var checkBoxBermuda: CheckBox

    private lateinit var editTextSapato: EditText
    private lateinit var editTextCamisa: EditText
    private lateinit var editTextBermuda: EditText

    private lateinit var buttonCalcular: Button

    private val valorSapato = 50.0
    private val valorCamisa = 30.0
    private val valorBermuda = 20.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpcoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Isso seria buscando os componentes pelo seu ID para serem manipulados
        checkBoxSapato = findViewById(R.id.checkBox_sapato)
        checkBoxCamisa = findViewById(R.id.checkBox_camisa)
        checkBoxBermuda = findViewById(R.id.checkBox_bermuda)

        editTextSapato = findViewById(R.id.editText_qntS)
        editTextCamisa = findViewById(R.id.editText_qntC)
        editTextBermuda = findViewById(R.id.editText_qntB)

        buttonCalcular = findViewById(R.id.button_calcular)

        //Aqui foi feito um evento de click em cada checkbox para verificar seu estado
        checkBoxSapato.setOnCheckedChangeListener { _, isChecked ->
            editTextSapato.isEnabled = isChecked
            if (isChecked) {
                binding.editTextQntS.setText("1")
                binding.textViewValorSapato.text = "R$ ${verificarValor(valorSapato)}"
            } else {
                editTextSapato.text.clear()
                binding.textViewValorSapato.text = ""
            }
        }

        checkBoxCamisa.setOnCheckedChangeListener { _, isChecked ->
            editTextCamisa.isEnabled = isChecked
            if (isChecked) {
                binding.editTextQntC.setText("1")
                binding.textViewValorCamisa.text = "R$ ${verificarValor(valorCamisa)}"
            } else {
                editTextCamisa.text.clear()
                binding.textViewValorCamisa.text = ""
            }
        }

        checkBoxBermuda.setOnCheckedChangeListener { _, isChecked ->
            editTextBermuda.isEnabled = isChecked
            if (isChecked) {
                binding.editTextQntB.setText("1")
                binding.textViewValorBermuda.text = "R$ ${verificarValor(valorBermuda)}"
            } else {
                editTextBermuda.text.clear()
                binding.textViewValorBermuda.text = ""
            }
        }

        //Aqui foi feito um evento de click no botao calcular
        buttonCalcular.setOnClickListener {


            var total = 0.0
            if (checkBoxSapato.isChecked) {
                total += valorSapato * editTextSapato.text.toString().toDouble()
            }
            if (checkBoxCamisa.isChecked) {
                total += valorCamisa * editTextCamisa.text.toString().toDouble()
            }
            if (checkBoxBermuda.isChecked) {
                total += valorBermuda * editTextBermuda.text.toString().toDouble()
            }
            /*Esta linha cria um Toast, que é uma pequena mensagem que aparece na parte inferior da tela
            por um curto período de tempo. Basta comenta-lo para desabilita-lo
             */
            //Toast.makeText(this, "O valor total é: $total", Toast.LENGTH_SHORT).show()


            /*Esse trecho passa as informaçoes para a activity que receberá estes dados que neste caso
            é a activity ResumoActivity, logo abaixo da bariavel contem os extras que está adicionando
            um extra ao Intent chamado “valorSapato”. Quando a ResumoActivity for iniciada, ela poderá
            recuperar esse extra para obter o valor do sapato.
             */
            var i = Intent(this, ResumoActivity::class.java)
            i.putExtra("valorSapato", valorSapato)
            i.putExtra("qtdSapato", editTextSapato.text.toString())
            i.putExtra("valorCamisa", valorCamisa)
            i.putExtra("qtdCamisa", editTextCamisa.text.toString())
            i.putExtra("valorBermuda", valorBermuda)
            i.putExtra("qtdBermuda", editTextBermuda.text.toString())
            startActivity(i)
        }
    }

    private fun verificarValor(vlProduto : Double) : String {
        val b = vlProduto.toString().indexOf(".") > 2
        if (b){
            return vlProduto.toString().replace(".", ",")
        }
        return vlProduto.toString().replace(".", ",") + "0"
    }
}
