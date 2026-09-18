package lab8;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.concurrent.*;

public class ParallelMonteCarloPi {

    private static final long TOTAL_ITERATIONS = 1_000_000_000L;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int threads = Runtime.getRuntime().availableProcessors();
        if (args.length > 0) {
            try {
                threads = Integer.parseInt(args[0]);
                if (threads <= 0) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.err.println("Помилка: кількість потоків має бути цілим додатним числом.");
                return;
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        long baseItersPerThread = TOTAL_ITERATIONS / threads;
        long remainder = TOTAL_ITERATIONS % threads;

        long startTime = System.nanoTime();


        Future<Long>[] futures = new Future[threads];
        for (int i = 0; i < threads; i++) {
            long iterations = baseItersPerThread + (i == 0 ? remainder : 0);
            futures[i] = executor.submit(() -> {
                long insideCount = 0;

                ThreadLocalRandom random = ThreadLocalRandom.current();

                for (long j = 0; j < iterations; j++) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();
                    if (x * x + y * y <= 1.0) {
                        insideCount++;
                    }
                }
                return insideCount;
            });
        }


        long totalInside = 0;
        for (Future<Long> future : futures) {
            totalInside += future.get();
        }

        long endTime = System.nanoTime();
        executor.shutdown();

        double pi = 4.0 * totalInside / TOTAL_ITERATIONS;
        double elapsedMs = (endTime - startTime) / 1_000_000.0;


        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setGroupingSeparator(',');

        DecimalFormat piFormat = new DecimalFormat("0.00000", symbols);
        DecimalFormat countFormat = new DecimalFormat("#,###", symbols);
        DecimalFormat timeFormat = new DecimalFormat("0.00", symbols);

        System.out.println("PI is " + piFormat.format(pi));
        System.out.println("THREADS " + threads);
        System.out.println("ITERATIONS " + countFormat.format(TOTAL_ITERATIONS));
        System.out.println("TIME " + timeFormat.format(elapsedMs) + "ms");
    }
}
