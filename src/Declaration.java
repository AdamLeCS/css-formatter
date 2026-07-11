public class Declaration {
    private String property;
    private String value;
    
    public Declaration(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public void print() {
        System.out.println(property + ": " + value + ";");
    }
}
