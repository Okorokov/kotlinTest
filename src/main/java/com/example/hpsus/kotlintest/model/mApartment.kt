package com.example.hpsus.kotlintest.model

class mApartment {
    var idApartment: Int = 0
    var indiApartment: Int = 0
    var indiHome: Int = 0
    var floor: Int = 0
    var area: Int = 0
    var visible: Int = 0

    constructor(idApartment: Int, indiApartment: Int, indiHome: Int, floor: Int, area: Int) {
        this.idApartment = idApartment
        this.indiApartment = indiApartment
        this.indiHome = indiHome
        this.floor = floor
        this.area = area
    }
    constructor(indiApartment: Int, indiHome: Int, floor: Int, area: Int) {
        this.idApartment = idApartment
        this.indiApartment = indiApartment
        this.indiHome = indiHome
        this.floor = floor
        this.area = area
    }
}