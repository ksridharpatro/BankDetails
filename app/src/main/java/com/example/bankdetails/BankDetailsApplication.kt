package com.example.bankdetails

import android.app.Application
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class BankDetailsApplication : Application() {

    // Sets the amount of time an idle thread waits before terminating
    private val keepAliveTime = 1L

    // Sets the Time Unit to seconds
    private val keepAliveTimeUnit: TimeUnit = TimeUnit.SECONDS

    /*
     * Gets the number of available cores
     * (not always the same as the maximum number of cores)
     */
    private val numberOfCore = Runtime.getRuntime().availableProcessors()

    // Instantiates the queue of Runnables as a LinkedBlockingQueue
    private val workQueue: BlockingQueue<Runnable> = LinkedBlockingQueue()

    // Creates a thread pool manager
    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        numberOfCore,  // Initial pool size
        numberOfCore,  // Max pool size
        keepAliveTime,
        keepAliveTimeUnit,
        workQueue
    )

    fun getThreadPoolExecutor(): ThreadPoolExecutor {
        return threadPoolExecutor
    }
}