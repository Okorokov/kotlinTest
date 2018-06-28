package com.example.hpsus.kotlintest.model

class mHome {
    var idHome: Int = 0
    var indiHome: Int = 0
    var nameHome: String = ""
    var floors: Int = 0
    var developerName: String = ""
    var visible: Int = 0

    constructor() {}
    constructor(idHome: Int, indiHome: Int, nameHome: String, floors: Int, developerName: String) {
        this.idHome = idHome
        this.indiHome = indiHome
        this.nameHome = nameHome
        this.floors = floors
        this.developerName = developerName
    }
    constructor(indiHome: Int, nameHome: String, floors: Int, developerName: String) {
        this.indiHome = indiHome
        this.nameHome = nameHome
        this.floors = floors
        this.developerName = developerName
    }
}