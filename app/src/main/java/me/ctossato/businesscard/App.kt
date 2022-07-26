package me.ctossato.businesscard

import android.app.Application
import me.ctossato.businesscard.data.AppDatabase
import me.ctossato.businesscard.data.BusinessCardRepository

class App: Application() {
    val database by lazy {AppDatabase.getDatabase(this)}
    val repository by lazy { BusinessCardRepository(database.businessDao()) }

}