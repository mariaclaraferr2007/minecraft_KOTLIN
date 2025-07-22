package com.example.minecraft22300570

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var spinner_material: Spinner
    private lateinit var edt_qtdeConstrutor: EditText
    private lateinit var btn_calcular: Button
    private lateinit var tv_resultado: TextView

    private val duracaoMateriais = mapOf(
        "Madeira" to 100,
        "Ouro" to 50,
        "Diamante" to 25,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinner_material = findViewById(R.id.tipos_de_material)
        edt_qtdeConstrutor = findViewById(R.id.quant_construtores)
        btn_calcular = findViewById(R.id.btn_consultar)
        tv_resultado = findViewById(R.id.resultado)

        val materials = duracaoMateriais.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, materials)
        spinner_material.adapter = adapter

        btn_calcular.setOnClickListener {
            calcularTempoConstrcao()
        }



    }

    private fun calcularTempoConstrcao(){
        val material_selecionado = spinner_material.selectedItem as? String
        val total_construtorStr = edt_qtdeConstrutor.text.toString()

        if (material_selecionado.isNullOrEmpty() || total_construtorStr.isEmpty()){
            tv_resultado.text = "Por favor selecione o material e insira o número de construtores."
            return
        }

        val total_construtor = total_construtorStr.toIntOrNull()
        if (total_construtor == null || total_construtor <= 0){
            tv_resultado.text = "Por favor, insira um número válido de construtores (maior que zero)."
            return
        }
        val tempo_base = duracaoMateriais[material_selecionado]
        if (tempo_base == null){
            tv_resultado.text = "Material inválido"
            return
        }
        var tempo_final = tempo_base.toDouble()
        for (i in 1 until total_construtor){
            tempo_final /= 2
        }

        tv_resultado.text = "Tempo estimado de construção: ${String.format("%.2f", tempo_final)} horas."




    }
}