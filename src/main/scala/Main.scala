package coegen
import java.io.File
import java.io.PrintWriter
import java.io.IOException
import javax.imageio.ImageIO

object Main {
    def main(args: Array[String]): Unit = {
        if(args.length != 1) {
            println("Incorrect number of arguments.")
            println("Usage: coegen <image>")
            println("Creates: <image>.coe")
            sys.exit(1)
        }
        val file = args(0)
        val src = new File(file)
        val coe = new File(file+".coe")
        if(!(src.canRead && coe.createNewFile)) {
            println("File access error")
            sys.exit(1)
        }
        println(s"Generating $file.coe ...")
        val pw = new PrintWriter(coe)
        pw.print(s"; Generated from $file\n")
        pw.print("memory_initialization_radix=16;\n")
        pw.print("memory_initialization_vector=\n")
        val img = ImageIO.read(src)
        val (h, w) = (img.getHeight, img.getWidth)
        for(y <- 0 to h-1) {
            for(x <- 0 to w-1) {
                val a = (img.getRGB(x,y) & 0xF0000000)
                val r = (img.getRGB(x,y) & 0x00F00000)
                val g = (img.getRGB(x,y) & 0x0000F000)
                val b = (img.getRGB(x,y) & 0x000000F0)
                var rgb = (r >>> 12)|(g >>> 8)|(b >>> 4)
                if(rgb == 0) rgb += 1
                if(a == 0) rgb = 0
                if(y == h-1 && x == w-1) pw.print(f"$rgb%03x;")
                else pw.print(f"$rgb%03x,\n")
            }
        }
        val px = img.getHeight * img.getWidth
        println(f"Done, $px%d pixels (12 bits) written.")
        pw.close()
    }
}
