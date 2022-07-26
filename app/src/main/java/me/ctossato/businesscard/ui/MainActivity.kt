package me.ctossato.businesscard.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import me.ctossato.businesscard.App
import me.ctossato.businesscard.data.BusinessCard
import me.ctossato.businesscard.databinding.ActivityMainBinding
import me.ctossato.businesscard.util.Image


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    //injetando o repositorio no viewModel
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val rvAdapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvCardsList.setHasFixedSize(true)
        binding.rvCardsList.layoutManager = LinearLayoutManager(this)


        binding.rvCardsList.adapter = rvAdapter
        getAllBusinessCard()

        binding.btAddBusinessCard.setOnClickListener{
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        rvAdapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
        rvAdapter.listenerDelete = { card ->
            deleteConfirm(this@MainActivity, card)
        }
    }

    private fun deleteConfirm(context: Context, card: BusinessCard) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(me.ctossato.businesscard.R.string.msg_title_bcard_delete))
        builder.setMessage(getString(me.ctossato.businesscard.R.string.msg_card_delete))
        builder.setCancelable(false)
        builder.setPositiveButton(getString(me.ctossato.businesscard.R.string.btn_delete),
            DialogInterface.OnClickListener { dialogInterface, _ ->
                mainViewModel.delete(card)
                dialogInterface.dismiss()
            })
        builder.setNegativeButton(getString(me.ctossato.businesscard.R.string.btn_cancel),
            DialogInterface.OnClickListener { dialogInterface, _ ->
                Toast.makeText(applicationContext, getString(me.ctossato.businesscard.R.string.msg_cancelled), Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            })
        builder.show()
    }


    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this, { businessCards ->
            rvAdapter.submitList(businessCards)
        })
    }
}
