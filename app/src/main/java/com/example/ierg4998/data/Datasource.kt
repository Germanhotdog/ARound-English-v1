package com.example.ierg4998.data

import com.example.ierg4998.R
import com.example.ierg4998.model.HomeUI


class Datasource() {
    fun loadAffirmations(): List<HomeUI> {
        return listOf<HomeUI>(
            HomeUI(R.string.AR, R.drawable.ar),
            HomeUI(R.string.OCR, R.drawable.ocr),
            HomeUI(R.string.Note, R.drawable.notes)


        )
    }
}