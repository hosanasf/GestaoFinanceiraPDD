package br.com.gestaofinanceirapdd.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lancamentos")
data class Lancamento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val categoria: String,
    val valor: Double,
    val tipo: String,
    val data: String,
    val observacao: String = ""
)
