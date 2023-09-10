// Define the Dimensionable interface
interface Dimensionable {
    fun setDimensions(vararg dimensions: Double)
    fun printDimensions()
}

abstract class Shape(_name: String) : Dimensionable {
    val name = _name

     abstract override fun setDimensions(vararg dimensions: Double)
    abstract override fun printDimensions()
    abstract fun getArea(): Double
}

// Create the Square class
class Square(_name: String) : Shape(_name) {
    private var length: Double = 0.0
    private var height: Double = 0.0

    constructor(_name: String, _length: Double, _height: Double) : this(_name) {
        setDimensions(_length, _height)
    }

    override fun setDimensions(vararg dimensions: Double) {
        if (dimensions.size == 2) {
            length = dimensions[0]
            height = dimensions[1]
        }
    }

    override fun printDimensions() {
        println("Square: Length = $length, Height = $height")
    }

    override fun getArea(): Double {
        return length * height
    }
}

// Create the Circle class
class Circle(_name: String) : Shape(_name) {
    private var radius: Double = 0.0

    constructor(_name: String, _radius: Double) : this(_name) {
        setDimensions(_radius)
    }

    override fun setDimensions(vararg dimensions: Double) {
        if (dimensions.size == 1) {
            radius = dimensions[0]
        }
    }

    override fun printDimensions() {
        println("Circle: Radius = $radius")
    }

    override fun getArea(): Double {
        return Math.PI * radius * radius
    }
}

// Create the Triangle class (marked as open for inheritance)
open class Triangle(_name: String) : Shape(_name) {
    private var sideA: Double = 0.0
    private var sideB: Double = 0.0
    private var sideC: Double = 0.0
    constructor(_name: String, sideA: Double, sideB: Double, sideC: Double) : this(_name) {
        this.sideA = sideA
        this.sideB = sideB
        this.sideC = sideC
    }

    override fun setDimensions(vararg dimensions: Double) {
        if (dimensions.size == 3) {
            sideA = dimensions[0]
            sideB = dimensions[1]
            sideC = dimensions[2]
        }
    }

    override fun printDimensions() {
        println("Triangle: Side A = $sideA, Side B = $sideB, Side C = $sideC")
    }

    override fun getArea(): Double {
        val s = (sideA + sideB + sideC) / 2.0
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
    }
}

class EquilateralTriangle(_name: String,_side :Double) : Triangle(_name,_side,_side,_side)







fun main() {
    // Create instances of each class and store them in variables of type Shape
    val square: Shape = Square("MySquare")
    val circle: Shape = Circle("MyCircle")
    val triangle: Shape = Triangle("MyTriangle")

    val exTriangle: Shape = EquilateralTriangle("MyExTriangle", 10.0)



    // Create a list to hold the shapes
    val shapes = listOf(square, circle, triangle, exTriangle)

    // Prompt the user to enter dimensions for each object
    for (shape in shapes) {
        println("Enter dimensions for ${shape.name}:")
        shape.setDimensionsFromUserInput()
    }

    println("\nShape Information:")
    // Print the name, dimensions, and area for each object
    for (shape in shapes) {
        println("Name: ${shape.name}")
        shape.printDimensions()
        println("Area: ${shape.getArea()}\n")
    }
}

// Extension function to set dimensions from user input for any shape
fun Shape.setDimensionsFromUserInput() {
    when (this) {
        is Square -> {
            println("Enter length and height for the square (space-separated):")
            val input = readLine()
            if (input != null) {
                val (length, height) = input.split(" ").map { it.toDouble() }
                setDimensions(length, height)
            }
        }
        is Circle -> {
            println("Enter radius for the circle:")
            val input = readLine()
            if (input != null) {
                val radius = input.toDouble()
                setDimensions(radius)
            }
        }
        is Triangle, is EquilateralTriangle -> {
            println("Enter three side lengths for the triangle (space-separated):")
            val input = readLine()
            if (input != null) {
                val (sideA, sideB, sideC) = input.split(" ").map { it.toDouble() }
                setDimensions(sideA, sideB, sideC)
            }
        }
    }
}
