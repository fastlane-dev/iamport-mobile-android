package com.iamport.sdk.data.sdk

sealed class IamportCertifcatePg(val pg: String, open val mid: String) {

    fun makeIamportCertification(merchantUid: String, company: String): IamPortCertification {
        return IamPortCertification(
            merchant_uid = merchantUid,
            pg = "${pg}.${mid}",
            company = company,
        )
    }

    data class Inisis(override val mid: String) : IamportCertifcatePg("inicis_unified", mid)
    data class Danal(override val mid: String) : IamportCertifcatePg("danal", mid)

}