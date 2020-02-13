package lombokexamples.constructors;

public class VanilaJavaConstructors<T> {
    private int x, y;

    private T description;

    private VanilaJavaConstructors(T description) {
        if (description == null) throw new NullPointerException("description");
        this.description = description;
    }

    public static <T> VanilaJavaConstructors<T> of(T description) {
        return new VanilaJavaConstructors<T>(description);
    }

    @java.beans.ConstructorProperties({"x", "y", "description"})
    protected VanilaJavaConstructors(int x, int y, T description) {
        if (description == null) throw new NullPointerException("description");
        this.x = x;
        this.y = y;
        this.description = description;
    }

    public static class NoArgsExample {
      private String field;
        public NoArgsExample() {
        }
    }
}
