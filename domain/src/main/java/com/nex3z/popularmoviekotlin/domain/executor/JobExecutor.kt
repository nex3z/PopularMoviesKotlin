package com.nex3z.popularmoviekotlin.domain.executor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class JobExecutor : ThreadExecutor {

    private val threadPoolExecutor: ThreadPoolExecutor =
            ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
                    LinkedBlockingQueue<Runnable>(), JobThreadFactory())

    override fun execute(runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }

}
