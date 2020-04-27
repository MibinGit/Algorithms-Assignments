package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * This code has been fleshed out by Mibin Zhu. Thanks very much.
 * TODO tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 1000;
    public static Executor executor = Executors.newFixedThreadPool(7);

    public static void sort(int[] array, int from, int to)
    {
        if (to - from < cutoff)
        {
            Arrays.sort(array, from, to);
        }

        else
        {
            //Executor executor=Executors.newFixedThreadPool(8);
            CompletableFuture<int[]> parsort1 = parsort(array, from, from + (to - from) / 2, executor); // TO IMPLEMENT
            CompletableFuture<int[]> parsort2 = parsort(array, from + (to - from) / 2, to, executor); // TO IMPLEMENT
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                int[] result = new int[xs1.length + xs2.length];
                // TO BE IMPLEMENTED ...
                int xs1index = 0;
                int xs2index = 0;
                for(int i = 0; i < xs1.length + xs2.length; i++)
                {
                    if(xs1index >= xs1.length)
                    {
                        result[i] = xs2[xs2index++];
                    }

                    else if(xs2index >= xs2.length)
                    {
                        result[i] = xs1[xs1index++];
                    }

                    else if(xs1[xs1index] >= xs2[xs2index])
                    {
                        result[i] = xs2[xs2index++];
                    }

                    else
                    {
                        result[i] = xs1[xs1index++];
                    }
                }
                // ... END IMPLEMENTATION
                return result;
            });

            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
//            System.out.println("# threads: "+ ForkJoinPool.commonPool().getRunningThreadCount());
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to,Executor executor)
    {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];
                    // TO BE IMPLEMENTED ...
                    for(int i = 0; i < to-from; i++)
                    {
                        result[i] = array[i+from];
                    }
                    sort(result,0,to-from);
                    // ... END IMPLEMENTATION
                    return result;
                },executor
        );
    }
}