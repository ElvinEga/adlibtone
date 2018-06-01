package com.twisac.apps.adlibtone.dto


class Header {


    var date: String? = null
    var header: String? = null
    var content: String? = null
    var image: String? = null

    constructor(date: String?, header: String?, content: String?, image: String?) {
        this.date = date
        this.header = header
        this.content = content
        this.image = image
    }
}
