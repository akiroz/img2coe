## img2coe

Image to Xilinx Coefficient converter

### Running

1. Install Java (7 or higher).
2. Download compiled jar [here](https://github.com/akiroz/img2coe/raw/master/build/libs/img2coe.jar).
3. Run `java -jar img2coe.jar my_img.png` (creates `my_img.png.coe` if it doesn't exist)

### Supported formats

Image: JPEG, PNG, BMP, GIF

Colors: Currently only supports 12-bit RBG

Transparency: 0% alpha converted to 0x000, black converted to 0x001.
