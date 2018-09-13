import sys
import struct
import numpy
import matplotlib.pyplot as plt

from PIL import Image


def analyse(in_file, channel):
    """Statistical analysis of an image to detect LSB steganography"""
    '''
    - Split the image into blocks.
    - Compute the average value of the LSBs for each block.
    - The plot of the averages should be around 0.5 for zones that contain
      hidden encrypted messages (random data).
    '''
    BS = 1000    # Block size 
    img = Image.open(in_file)
    (width, height) = img.size
    print "[+] Image size: %dx%d pixels" % (width, height)
    conv = img.convert("RGBA").getdata()

    # Extract LSBs
    vr = [] # Red LSBs
    vg = [] # Green LSBs
    vb = [] # Blue LSBs
    va = [] # Alpha LSBs
    for h in range(height):
        for w in range(width):
            (r, g, b, a) = conv.getpixel((w, h))
            vr.append(r & 1)
            vg.append(g & 1)
            vb.append(b & 1)
            va.append(a & 1)

    # Average color channel LSB per each block
    avgR = []
    avgG = []
    avgB = []
    avgA = []
    for i in range(0, len(vr), BS):
        avgR.append(numpy.mean(vr[i:i + BS]))
        avgG.append(numpy.mean(vg[i:i + BS]))
        avgB.append(numpy.mean(vb[i:i + BS]))
        avgA.append(numpy.mean(va[i:i + BS]))

    # Nice plot 
    numBlocks = len(avgR)
    blocks = [i for i in range(0, numBlocks)]
    plt.axis([0, len(avgR), 0, 1])
    plt.ylabel('Average LSB per block')
    plt.xlabel('Block number')

    if channel == 'red':
        plt.plot(blocks, avgR, 'ro')
    elif channel == 'green':
        plt.plot(blocks, avgG, 'go')
    elif channel == 'blue':
        plt.plot(blocks, avgB, 'bo')
    elif channel == 'alpha':
        plt.plot(blocks, avgA, 'yo')
    else:
        print "Unknown channel %s" % (channel,)
    
    plt.show()

def usage(progName):
    print "Usage:"
    print "  %s <image_file> <color_channel>" % progName
    sys.exit()
    
if __name__ == "__main__":
    if len(sys.argv) < 3:
        usage(sys.argv[0])
    analyse(sys.argv[1], sys.argv[2])
        
