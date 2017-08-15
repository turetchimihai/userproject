public class Verifier {
    public boolean verifierLength(String string) {
        if (string.length() < 6) {
            return false;
        } else {
            return true;
        }
    }
}
