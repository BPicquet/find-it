package fr.iutlens.picquet.findit

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var quizs = ArrayList<Quiz>()
    var numberOfGoodAnswers: Int = 0
    var currentQuizIndex: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quizs.add(Quiz("Quel super héros porte un bouclier ?", "Iron Man", "Spiderman", "Captain America", "Drax", 3))
        quizs.add(Quiz("Combien y'a t'il de pierre de l'infini ?", "4", "10", "7", "6", 4))
        quizs.add(Quiz("Qui est Groot ?", "Un arbre", "Une tasse", "Un humain", "Un marsien", 1))
        quizs.add(Quiz("Où Iron man a t'il créé sa première armure ?", "Dans sa maison", "Dans une grotte", "Dans son labo", "Au bar", 2))
        quizs.add(Quiz("Quel relation unit Steve Rogers et Bucky ?", "Ils sont frères", "Ils sont ami d'enfance", "Ils sont amants", "Ils sont cousins", 2))
        quizs.add(Quiz("Comment se nomme la fille de Scott Lang ?", "Laure", "Megane", "Sarah", "Cassie", 4))
        quizs.add(Quiz("Quel est le vrai métier de Stephen Strange ?", "Docteur", "Chirurgien", "Dentiste", "Professeur", 2))
        quizs.add(Quiz("Comment s'appelle la soeur de Black Panther ?", "Shuri", "Suri", "Surimi", "Shuiri", 1))
        quizs.add(Quiz("De quoi Heimdall est le gardien ?", "Le Bifröst", "Le Tesseract", "Le marteau", "Odin", 1))
        quizs.add(Quiz("Qui ne fait pas partie du MCU ?", "Spiderman", "Thor", "Batman", "Odin", 2))

        showQuestion(quizs.get(currentQuizIndex))
    }

    fun showQuestion(quiz: Quiz){
        chronometer.start()
        txtQuestion.setText(quiz.question)
        answer1.setText(quiz.answer1)
        answer2.setText(quiz.answer2)
        answer3.setText(quiz.answer3)
        answer4.setText(quiz.answer4)
    }

    fun onClickBtnReturn(view: View){
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    fun handleAnswer(answerID: Int){
        val quiz = quizs.get(currentQuizIndex)

        if(quiz.isCorrect(answerID)) {
            numberOfGoodAnswers++
            Toast.makeText(this, "+1", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "+0", Toast.LENGTH_SHORT).show()
        }

        currentQuizIndex++
        counterQuestion.text = "Question " + currentQuizIndex

        if(currentQuizIndex >= quizs.size){
            // Partie terminée (stopper timer et afficher score et temps)
            chronometer.stop()
            val elapsedMillis: Long = SystemClock.elapsedRealtime() - chronometer.getBase()
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Partie terminée")
            alert.setMessage("Tu as eu " + numberOfGoodAnswers + " bonnes réponses en " + (elapsedMillis/1000) + " secondes.")
            alert.setPositiveButton("ok"){ dialogInterface: DialogInterface?, i: Int ->
                finish()
            }
            alert.show()
        } else{
            showQuestion(quizs.get(currentQuizIndex))
        }
    }

    fun onClickAnswerOne(view: View){
        handleAnswer(1)
    }
    fun onClickAnswerTwo(view: View){
        handleAnswer(2)
    }
    fun onClickAnswerThree(view: View){
        handleAnswer(3)
    }
    fun onClickAnswerFour(view: View){
        handleAnswer(4)
    }
}