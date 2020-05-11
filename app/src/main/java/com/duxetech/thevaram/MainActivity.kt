package com.duxetech.thevaram

import Book
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.*
import bookList
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileNotFoundException




class MainActivity : AppCompatActivity(),
    AdapterView.OnItemSelectedListener {


    var spinner1 : Spinner? = null
    var spinner2 : Spinner? = null
    var contentView : TextView? = null
    var book = 0
    var chapter = 0

    var booktitles = arrayOf("திருஞானசம்பந்தர் தேவாரம் 1",
        "திருஞானசம்பந்தர் தேவாரம் 2",
        "திருஞானசம்பந்தர் தேவாரம் 3",
        "திருநாவுக்கரசர் தேவாரம் 1",
        "திருநாவுக்கரசர் தேவாரம் 2",
        "திருநாவுக்கரசர் தேவாரம் 3",
        "சுந்தரர் தேவாரம்",
        "மாணிக்கவாசகர் திருவாசகம்")

    val titles = arrayOf("முதல் திருமுறை","இரண்டாம் திருமுறை", "மூன்றாம் திருமுறை", "நான்காம் திருமுறை", "ஐந்தாம் திருமுறை", "ஆறாம் திருமுறை", "ஏழாம் திருமுறை", "எட்டாம் திருமுறை")


    val defText = "தென்னாடுடைய சிவனே போற்றி" +
            "எந்நாட்டவர்க்கும் இறைவா போற்றி!"


    lateinit var adapter1 : ArrayAdapter<String>
    lateinit var adapter2 : ArrayAdapter<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.title = "திருமுறைகள்"

        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)
        contentView = findViewById(R.id.contentView)

         adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item,booktitles)

        spinner1!!.setOnItemSelectedListener(this)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinner1!!.adapter = adapter1

        Log.i("books",bookList.size.toString())


        contentView!!.movementMethod = ScrollingMovementMethod()

        spinner1!!.onItemSelectedListener


        


    }

    private fun loadFiles(book : Int, chapter : Int):String{

        val book = book + 1
        val file = "$book/$chapter.txt"

        var txt = ""
        try {
             txt = application.assets.open(file).bufferedReader().
                use {
                    it.readText()
                }

      //      Log.i("om",txt)

        } catch (e:FileNotFoundException) {

        }
        return  txt

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }



    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        print(position)

        if (parent!!.id == spinner1!!.id) {
            book = position
            this.title = titles[position]
            loadSpinner2()
            if (chapter == 0) {
                contentView!!.text = defText
            }

        }  else
        if (parent!!.id == spinner2!!.id) {
            chapter = position
            contentView!!.text = loadFiles(book,chapter)
            contentView!!.scrollTo(0,0)
            contentView!!.append("           " +
                    "திருச்சிற்றம்பலம்")
            if (chapter == 0) {
                contentView!!.text = defText
            }

        }

    }



    fun loadSpinner2(){
        adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item,bookList[book].titles)
        spinner2!!.setOnItemSelectedListener(this)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2!!.adapter = adapter2

    }




}
