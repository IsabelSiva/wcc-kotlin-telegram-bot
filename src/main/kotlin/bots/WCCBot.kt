package bots

import com.vdurmont.emoji.EmojiParser
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendAudio
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.OutputStreamWriter

class WCCBot : TelegramLongPollingBot() {

    //recebe o nome do bot
    override fun getBotUsername(): String {
        //return bot username
        // If bot username is @HelloKotlinBot, it must return
        return "Dona_Maria_bot"
    }
    //recebe o token do bot
    override fun getBotToken(): String {
        // Return bot token from BotFather
        return ""
    }
    //recebe a entrada do usuário
    override fun onUpdateReceived(update: Update?) {
        // We check if the update has a message and the message has text
        val nameSender = update?.message?.from?.firstName
        val chatId = update?.message?.chatId.toString()
        val messageCommand = update?.message?.text

        try {
            when (messageCommand) {
                "/start" -> execute(
                    SendDocument().apply {
                        this.chatId = chatId
                        this.caption = mainMenu(nameSender)
                        this.document =
                            InputFile().setMedia("https://media.giphy.com/media/CIJsP7PsWvZM4/giphy.gif")
                        this.parseMode = "MarkdownV2"
                    }

                )
                "/add" -> {
                    val sendDocument = SendMessage().apply {
                        this.chatId = chatId
                        this.text = EmojiParser.parseToUnicode("*Let's do it*")
                        this.parseMode = "MarkdownV2"
                    }

                    execute(sendDocument)
                }
                    "/lista" -> {
                val sendDocument = SendMessage().apply {
                    this.chatId = chatId
                    this.text = EmojiParser.parseToUnicode("*Um dia mostro sua lista aqui!!!*")
                    this.parseMode = "MarkdownV2"
                }

                execute(sendDocument)
            }
                else -> {
                    val sendDocument = SendMessage().apply {
                        this.chatId = chatId
                        this.text = EmojiParser.parseToUnicode("num rolou  :(")
                        this.parseMode = "MarkdownV2"
                    }

                    execute(sendDocument)
                }
            }
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
    private fun mainMenu(nameSender: String?) = EmojiParser.parseToUnicode(
        """ 
        Oi, seja bem vindo\(a\) $nameSender        
        \/start --> Para iniciar uma conversa comigo :)        
        \/lista --> Mostra a sua lista de compras :)       
        \/add --> Adiciona item à sua lista       
        """.trimIndent()
    )
}

