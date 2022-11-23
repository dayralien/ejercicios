class Challenge5() {

    fun aspectRatio(url: String) {

        Thread {
            var aspectRationStr: String? = null

            val url = URL(url)
            val bitmap = BitmapFactory.decodeStream(url.openStream())

            val height = bitmap.height
            val width = bitmap.width
            val aspectRatio = rationalAspectRatio(height.toDouble() / width.toDouble())
            aspectRationStr = "${aspectRatio.second}:${aspectRatio.first}"

            aspectRationStr?.let { ratio ->
                println("El aspect ratio es ${ratio}")
            } ?: run {
                println("No se ha podido calcular el aspect ratio")
            }
        }.start()
    }

    data class Quadruple(val h1: Int, val k1: Int, val h: Int, val k: Int)

    private fun rationalAspectRatio(aspectRatio: Double): Pair<Int, Int> {
        val precision = 1.0E-6
        var x = aspectRatio
        var a = x.roundToInt()
        var q = Quadruple(1, 0, a, 1)

        while (x - a > precision * q.k.toDouble() * q.k.toDouble()) {
            x = 1.0 / (x - a)
            a = x.roundToInt()
            q = Quadruple(q.h, q.k, q.h1 + a * q.h, q.k1 + a * q.k)
        }
        return Pair(q.h, q.k)
    }

}