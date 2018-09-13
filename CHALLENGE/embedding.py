
"""
Embed payload data steganographically in an image.
"""

import sys
import struct
import numpy

from PIL import Image

def decompose(data):
    '''Decompose binary data into a list of bits'''
    bits = []                           # Initialize an empty list of bits
    fileSize = len(data)                # Store the file size
    bytes = [ord(b) for b in struct.pack("i", fileSize)]
    bytes += [ord(b) for b in data]     # Convert each byte into an integer [0-255]
    for b in bytes:                     # For each integer byte value..
        for i in [7,6,5,4,3,2,1,0]:     # ..for each bit position..
            bits.append((b >> i) & 0x1) # extract the current bit and append it to the list
    return bits

def set_bit(n, i, x):
    """Set the i-th bit of n to x"""
    mask = 1 << i           # 
    n &= ~mask              # 
    if x:                   # 
        n |= mask           # 
    return n

def embed(imgFilename, payloadFilename):
    """Embed payload file into LSB bits of an image"""
    # Process source image
    img = Image.open(imgFilename)
    (width, height) = img.size          # Get the image width and height
    conv = img.convert("RGBA").getdata()# Treat the image as RGBA
    print "[*] Input image size:    %dx%d pixels" % (width, height)
    maxSize = width * height / 8        # Per image byte we can only store 1 bit
    print "[*] Usable payload size: %9d bytes" % (maxSize)

    payload = open(payloadFilename, "rb").read()
    print "[*] Payload size:        %9d bytes" % (len(payload),)

    # Process data from payload file
    bits = decompose(payload)
    
    if (len(payload) > maxSize):
        print "[-] Cannot embed. Payload file too large"
        sys.exit()
        
    # Create output image
    steg_img = Image.new('RGBA',(width, height))
    data_img = steg_img.getdata()

    idx = 0                             # Position in the payload bits list
    for h in range(height):
        for w in range(width):
            (r, g, b, a) = conv.getpixel((w, h))
            # At this point, the variables r, g, b and a contain the values for
            # red, green, blue and alpha.

            # TODO: Fill in here (Hier ergaenzen)

            # Write the modified pixel data back to the image
            data_img.putpixel((w,h), (r, g, b, a))
            # Adapt the index for the payload bits list
            idx = idx + 1
    
    steg_img.save(imgFilename + "-stego.png", "PNG")
    print "[+] %s embedded successfully" % payloadFilename


def usage(progName):
    print "LSB steganogprahy. Hide files within least significant bits of images.\n"
    print "Usage:"
    print "  %s <img_file> <payload_file>" % progName
    sys.exit()
    
if __name__ == "__main__":
    if len(sys.argv) < 2:
        usage(sys.argv[0])
        
    embed(sys.argv[1], sys.argv[2])
