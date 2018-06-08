package com.minosai.archusers.utils

import androidx.work.Worker
import com.minosai.archusers.repo.CryptoRepo
import javax.inject.Inject

class RefreshWorker: Worker() {

    @Inject
    lateinit var cryptoRepo: CryptoRepo

    override fun doWork(): WorkerResult {
        cryptoRepo.refreshCryptos()
        NotificationHelper.notifyRefresh(applicationContext)
        return WorkerResult.SUCCESS
    }
}