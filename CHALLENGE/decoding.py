
"""
Extract payload data from a steganographic image.
"""

import sys
import struct
import numpy

from PIL import Image

def assemble(v):    
    """Assemble an array of bits into a binary file"""
    bytes = ""
    length = len(v)
    for idx in range(0, len(v)/8):
        byte = 0
        for i in range(0, 8):
            if (idx*8+i < length):
                byte = (byte<<1) + v[idx*8+i]        
        bytes = bytes + chr(byte)

    payload_size = struct.unpack("i", bytes[:4])[0]

    return bytes[4: payload_size + 4]

def extract(in_file, out_file):
    """Extract data embedded into LSB of the input file"""
    # Process source image
    img = Image.open(in_file)
    (width, height) = img.size
    conv = img.convert("RGBA").getdata()
    print "[+] Image size: %dx%d pixels." % (width, height)

    # Extract LSBs
    v = []
    for h in range(height):
        for w in range(width):
            (r, g, b, a) = conv.getpixel((w, h))
            v.append(r & 1)
    data_out = assemble(v)

    # Write decrypted data
    out_f = open(out_file, "wb").write(data_out)
    print "[+] Written extracted data to %s" % out_file


def usage(progName):
    print "LSB steganogprahy. Hide files within least significant bits of images.\n"
    print "Usage:"
    print "  %s <stego_file> <out_file>" % progName
    sys.exit()
    
if __name__ == "__main__":
    if len(sys.argv) < 2:
        usage(sys.argv[0])
    
    extract(sys.argv[1], sys.argv[2])
    
