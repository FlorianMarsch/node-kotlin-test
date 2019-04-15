class HelloWorld(){

    val greeting :String
    get(): String {
        val words = mutableListOf<String>()
        words.add("Hello,")
        words.add("world!")
        words.add("i am a beautiful butterfly...")

        return words.joinToString(separator = " ")
    }
}