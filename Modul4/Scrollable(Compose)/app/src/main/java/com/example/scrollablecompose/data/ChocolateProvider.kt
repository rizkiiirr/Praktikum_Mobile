package com.example.scrollablecompose.data

import com.example.scrollablecompose.R

object ChocolateProvider {
        val chocolateList = listOf(
            Chocolate(
                no = 1,
                title = "Dark Chocolate",
                description = "Dark chocolate is a form of chocolate made from cocoa solids, cocoa butter and sugar. " +
                        "It has a higher cocoa percentage than white chocolate, milk chocolate, and semisweet chocolate.",
                chocolateImageId = R.drawable.darkchocolate,
                btnLink = "https://en.wikipedia.org/wiki/Dark_chocolate"
            ),
            Chocolate(
                no = 2,
                title = "Milk Chocolate",
                description = "Milk chocolate is a form of solid chocolate containing cocoa, sugar and milk. " +
                        "Milk chocolate contains smaller amounts of cocoa solids than dark chocolates do, and (as with white chocolate) contains milk solids.",
                chocolateImageId = R.drawable.milkchocolate,
                btnLink = "https://en.wikipedia.org/wiki/Milk_chocolate"
            ),
            Chocolate(
                no = 3,
                title = "White Chocolate",
                description = "White chocolate is a chocolate made from cocoa butter, sugar and milk solids. " +
                        "It is ivory in color and lacks the dark appearance of most other types of chocolate as it does not contain the non-fat components of cocoa (cocoa solids).",
                chocolateImageId = R.drawable.whitechocolate,
                btnLink = "https://en.wikipedia.org/wiki/White_chocolate"
            ),
            Chocolate(
                no = 4,
                title = "Hazelnut Chocolate",
                description = "Hazelnut  chocolate adds a new dimension to the flavor by combining the flavor of chocolate with the distinctive hazelnut  nut. " +
                        "The hazelnut  provides a savory, crunchy, and slightly sweet touch that complements the richness of the chocolate.",
                chocolateImageId = R.drawable.huzelnutchocolate,
                btnLink = "https://kioskcokelat.com/blogs/news/perbedaan-coklat-dan-coklat-hazelnut"
            ),
            Chocolate(
                no = 5,
                title = "Mint Chocolate",
                description = "Mint chocolate (or chocolate mint) is a popular flavor of chocolate, made by adding a mint flavoring, such as peppermint, spearmint, or crème de menthe, to chocolate. " +
                        "Mint chocolate can be found in a wide variety of confectionery items, such as candy, mints, cookies, mint chocolate chip ice cream, hot chocolate, and others.",
                chocolateImageId = R.drawable.mintchocolate,
                btnLink = "https://en.wikipedia.org/wiki/Chocolate#Flavors"
            )
        )
    }