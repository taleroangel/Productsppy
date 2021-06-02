package grupo7.poo.exceptions;

public class NoInfoException extends Exception {

    private String classInWhich = "";

    public NoInfoException() {
    }

    public NoInfoException(String message) {
        super(message);
    }

    public NoInfoException(String classInWhich, boolean nullException) {
        this.classInWhich = classInWhich;
    }

    public void printCause() {
        System.err.println("Ocurrió un error en la clase: " + classInWhich + '\n' +
                                   "La variable de datos es null!" + '\n');
    }
}
