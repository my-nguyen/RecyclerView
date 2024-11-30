package com.nguyen.recyclerview

data class Contact(val name: String, val age: Int) {
    val imageUrl = "https://picsum.photos/150?random=$age"
    val imageUrlLandscape = "https://picsum.photos/300/150?random=$age"
}
