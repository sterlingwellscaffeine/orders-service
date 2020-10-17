import java.lang.Character.isWhitespace

class InputReader {
    // making the reasonable assumption for a number of different input formats including the way that it is presented in the question as well as other delimiters
    // including that it could be read in multiple lines, delimited by , [] and spaces
    // In addition, I am making the reasonable assumption that all of the inputs will be either "Apple" or "Orange" and that they will be properly spelled
    // and only in the English language (no language support for other languages)

    fun readLn(): String {
        return readLine()!!
    }
}
