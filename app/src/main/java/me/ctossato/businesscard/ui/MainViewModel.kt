package me.ctossato.businesscard.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.ctossato.businesscard.data.BusinessCard
import me.ctossato.businesscard.data.BusinessCardRepository
import java.lang.IllegalArgumentException

class MainViewModel(private val businessCardRepository: BusinessCardRepository): ViewModel() {

    fun insert(businessCard: BusinessCard){
        businessCardRepository.insert(businessCard)
    }

    fun getAll() : LiveData<List<BusinessCard>> {
        return businessCardRepository.getAll()

    }

    fun delete(businessCard: BusinessCard) {
        businessCardRepository.delete(businessCard)
    }
}

// Criada aqui por fins didaticos. Criada para injetar o viewModel "na mão" no provider
class MainViewModelFactory(private val repository: BusinessCardRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}