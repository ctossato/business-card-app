package me.ctossato.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import me.ctossato.businesscard.App
import me.ctossato.businesscard.data.BusinessCard
import me.ctossato.businesscard.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnClose.setOnClickListener{
            finish()
        }
        binding.btConfirm.setOnClickListener{
            val businessCard = BusinessCard(
                name = binding.etName.editText?.text.toString(),
                phone = binding.etPhone.editText?.text.toString(),
                email = binding.etEMail.editText?.text.toString(),
                company = binding.etCompany.editText?.text.toString(),
                backgroud_color = binding.etBackgroundColor.editText?.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, "Business card added successfully", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}