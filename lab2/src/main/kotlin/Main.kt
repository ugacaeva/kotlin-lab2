import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.text.StringBuilder

fun main() {
    //Второй способ - чтение и запись с помощью встроенных методов
    val task = File("C:\\Users\\asus\\IdeaProjects\\lab2\\src\\main\\task\\task.txt")
    val readFile = task.readText() //чтение файла task
    println("Файл task.txt :\n")
    println(readFile)

    val fixedFile = readFile.trim().replace("( )+".toRegex(), " ") //удаление лишних пробелов в файле task.txt
    val result = File("src/main/task/result.txt") //создание нового файла result.txt
    if (!result.exists()) { //проверка существования файла
        result.writeText(fixedFile) //создание и запись в новый файл
    }
    println("\nФайл result.txt :\n")
    println(result.readText())

    val resultDir = File("src/main/result")
    if (!resultDir.exists()) { //проверка
        resultDir.mkdir() //создание папки result
    }

    if (!File("src/main/result/result.txt").exists()) {
        Files.move(result.toPath(), Paths.get("src/main/result/result.txt")) //перемещение файла result.txt в папку result
    }

    val finalResult = File("src/main/result/final_result.txt")
    if (!finalResult.exists()) {
        File("src/main/result/result.txt").renameTo(finalResult) //переименование файла result.txt в final_result.txt
        println("\nФайл final_result.txt :\n${finalResult.readText()}\n")
    }


    //Первый способ - чтение и запись с помощью Input- и OutputStream
    var i:Int
    val str = StringBuilder()
    val fis = InputStreamReader(task.inputStream(), "UTF-8") //чтение
    println("\nЧтение файла task.txt (InputStream) :\n")
    while (fis.read().also { i = it } != -1) {
        print(i.toChar())
        str.append(i.toChar())
    }
    fis.close()

    val newFixed = str.trim().replace("( )+".toRegex(), " ")

    val result2 = File("src/main/task/result2.txt") //новый файл для записи
    val fos = OutputStreamWriter(result2.outputStream(), "UTF-8") //запись
    fos.write(newFixed)
    println("\nФайл result2.txt создан (запись OutputStream)\n")
    fos.close()


    //Третий способ - с помощью BufferedReader и BufferedWriter
    val str2 = StringBuilder()
    val fbr = FileInputStream(task).bufferedReader() //для чтения
    val lines = fbr.readLines()
    println("Чтение файла task.txt (BufferedReader): \n")
    lines.forEach {
        println(it)
        str2.append("\n$it")
    }
    fbr.close()

    val anotherFixed = str2.trim().replace("( )+".toRegex(), " ")

    val result3 = File("src/main/task/result3.txt")
    val fbw = FileOutputStream(result3).bufferedWriter() //для записи
    fbw.write(anotherFixed)
    println("\nФайл result3.txt создан (запись BufferedWriter)\n")
    fbw.close()
}