package com.fudex.plusit.views.view_models

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fudex.plusit.models.PlusitModel
import com.fudex.plusit.models.repositrys.PlursitRepositry
import kotlinx.coroutines.launch

class PlusitViewModel @ViewModelInject constructor(private val plursitRepositry: PlursitRepositry,
                                                   @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel()
{

    var lastId=0
    val plusits: MutableLiveData<List<PlusitModel>> = MutableLiveData<List<PlusitModel>>()
fun getplusit(id:Int){
    lastId=id
    viewModelScope.launch {
        plusits.value=plursitRepositry.getPlusit(id)
    }
}
fun insertNewPlusit(title:String,catId:Int) {
    viewModelScope.launch {
        plursitRepositry.insertPlusit(title,catId)
        getplusit(lastId)
    }
}

    fun updatePlusit(plusitModel: PlusitModel) {
        viewModelScope.launch {
            plursitRepositry.updatePlusit(plusitModel)
            getplusit(lastId)
        }
    }
        fun deletePlusit(plusitModel: PlusitModel){
            viewModelScope.launch {
                plursitRepositry.deleteplust(plusitModel)
                getplusit(lastId)
            }
}
}