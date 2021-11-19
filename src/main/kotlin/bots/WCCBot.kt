package bots

import com.vdurmont.emoji.EmojiParser
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendAudio
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class WCCBot : TelegramLongPollingBot() {
    val wellcome = """
        | Olá tudo ok? Escolha um comando para seguir!
        | /start - inicia esse bot que vos fala
        | /lista - cria uma lista de compras
        | /add - adiciona um intém na lista
        | /off - para encerrar minha graça
    """.trimMargin()
    //recebe o nome do bot
    override fun getBotUsername(): String {
        //return bot username
        // If bot username is @HelloKotlinBot, it must return
        return "Dona_Maria_bot"
    }
    //recebe o token do bot
    override fun getBotToken(): String {
        // Return bot token from BotFather
        return "2111072560:AAHFnGbbwctWRiCO0kQqk_IxMGyNnANNCYI"
    }
    //recebe a entrada do usuário
    override fun onUpdateReceived(update: Update?) {
        // We check if the update has a message and the message has text
        val nameSender = update?.message?.from?.firstName
        val chatId = update?.message?.chatId.toString()
        val messageCommand = update?.message?.text?.lowercase()

        try {
            if(messageCommand=="/start") {
                val sendDocument = SendDocument().apply {
                    this.chatId = chatId
                    this.caption = wellcome//getMessage(messageCommand)
                    this.document = InputFile().setMedia("https://media.giphy.com/media/dUlviyeJQJnAS8v9AT/giphy.gif")
                    this.parseMode = "MarkdownV2"
                }

                execute(sendDocument)
            } else {
                val sendDocument = SendMessage().apply {
                    this.chatId = chatId
                    this.text = EmojiParser.parseToUnicode("num funfou :(")
                    this.parseMode = "MarkdownV2"
                }

                execute(sendDocument)
            }
            if(messageCommand=="/add") {
                val sendDocument = SendDocument().apply {
                    this.chatId = chatId
                    this.caption = getMessage(messageCommand)
                    this.document = InputFile().setMedia("https://media.giphy.com/media/dUlviyeJQJnAS8v9AT/giphy.gif")
                    this.parseMode = "MarkdownV2"
                }

                execute(sendDocument)
            } else {
                val sendDocument = SendMessage().apply {
                    this.chatId = chatId
                    this.text = EmojiParser.parseToUnicode("num funfou :(")
                    this.parseMode = "MarkdownV2"
                }

                execute(sendDocument)
            }
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
    private fun getMessage(comand: String?) = when(comand){
        "/start" -> wellcome
        "/off" -> "Encerra o bot"
        "/lista" -> "Cria lista de compras"
        "/add" -> "Adiciona itém a lista"
        else -> "Sai daí maluca"
    }
}

