package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /**
     * Testando modificações com o gitHub desktop
     */


    private lateinit var binding: ActivityMainBinding
    private lateinit var texto:String

    private val nome_nickname:MeuNome = MeuNome("AboutMe App")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.dados = nome_nickname

        //deixar o texto da bio sem aparecer o nome
        resetText()
        //listener para o botão
        binding.doneButton.setOnClickListener { v: View? ->
            changeName(binding.doneButton)
        }

        //listener para nickName_view chamar o botão novamente
        binding.nickNameView.setOnClickListener{ v: View? ->
            reWrite()
        }

    }

    //button click handler
    private fun changeName(view: View){
        //mudar o nome de nickName_view
        binding.nickNameView.text = binding.nicknameEdit.text
        texto = getString(R.string.bio,binding.nicknameEdit.text)


        binding.apply{
            //adicionar o nickname em nickName_view
            nome_nickname.nickname = nicknameEdit.text.toString()
            invalidateAll()
            binding.bioText.text = texto
            //mudar a visibilidade de nickName_view
            binding.nickNameView.visibility = View.VISIBLE
            //mudar a visibilidade de name_text
            //binding.nameText.visibility = View.GONE
            //mudar a visibilidade do done_button
            binding.doneButton.visibility = View.GONE
            //mudar a visibilidade do nickname_edit
            binding.nicknameEdit.visibility = View.GONE
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun reWrite(){
        binding.apply{
            //mudar a visibilidade do done_button
            binding.doneButton.visibility = View.VISIBLE
            //mudar a visibilidadedo nickname_edit
            binding.nicknameEdit.visibility = View.VISIBLE
            //remover nome atribuido a nickname_edit para mostrar a hint
            binding.nicknameEdit.setText("")
            //mudar a visibilidade de nickName_view
            binding.nickNameView.visibility = View.GONE}
        resetText()
    }

    private fun resetText(){
        texto = getString(R.string.bio,"")
        binding.bioText.text = texto
    }
}