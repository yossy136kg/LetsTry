package self.chatchup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Optional02 {
	// https://qiita.com/ka2kama/items/83927a840b406bffe36f
	
    public static void main(String[] args) {
        // ラムダ式
        Optional<List<Integer>> xs = traverse(List.of("1", "100", "30"), x -> safeParseInt(x));
        // メソッド参照
        Optional<List<Integer>> ys = traverse(List.of("1", "3", "hoge", "0"), Optional02::safeParseInt);

        System.out.println(xs); // Optional[[1, 100, 30]]
        System.out.println(ys); // Optional.empty
    }

    public static Optional<Integer> safeParseInt(String s) {
        try {
            Integer n = Integer.valueOf(s);
            return Optional.of(n);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static <T, R> Optional<List<R>> traverse(List<T> xs, Function<T, Optional<R>> f) {
        List<R> ys = new ArrayList<>();
        for (T x : xs) {
            Optional<R> o = f.apply(x);
            if(o.isEmpty()) {
                return Optional.empty();
            }
            ys.add(o.get());
        }
        return Optional.of(ys);
    }

}
