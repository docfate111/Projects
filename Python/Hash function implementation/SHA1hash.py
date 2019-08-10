def sha1(m):
	#first initialize the vectors:
	h0 = 0x67452301
	h1 = 0xEFCDAB89
	h2 = 0x98BADCFE
	h3 = 0x10325476
	h4 = 0xC3D2E1F0
	message=""
	for letter in m:
		k='{0:08b}'.format(ord(letter))
		#converting the characters of the message m into binary
		while len(k)<8:
			k="0"+k
		if len(k)==8:
			message+=k
		#padding the message with 0s so that the binary is in packets of 8 bits
	c=len(message)
	strOfbits=message+"1"
	#adding 1 to the bits
	l=len(strOfbits)%512
	#pad with 0s until its length in 448 mod 512
	strOfbits+=(448-l)*"0"
	#convert length of array to binary in 64 bits then adding it to the message m and append it to the encrypted message m
	strOfbits+='{0:064b}'.format(c-1)
	#now the length is 512 bits (448+64)
	def chunks(l, n):
		return [l[i:i+n] for i in range(0, len(l), n)]
	for i in chunks(strOfbits, 512): 
	#break the array into chunk of 512 characters then into 16 32bit words:
		words = chunks(i, 32)
		#creating an empty array for 80 bits
		w=[0]*80
		#assiging the binary from the encrypted message to each word
		for n in range(0, 16):
			w[n] = int(words[n], 2)
		#pieces of the word are xored with each other(all the same length)
		#left rotate (move the elements of a set by 1 over last element becomes first) xor c
		for i in range(16, 80):
			w[i] = (w[i-3]^w[i-8]^w[i-14]^w[i-16])<<1  
			a, b, c, d, e = h0, h1, h2, h3, h4
	#main loop: the only place in the code where sha family differs
		for i in range(80):
			if i<=19:	
				f=(b&c)|((~b)&d)
				k=0x5A827999
			elif i<=39:
				f=(b^c)^d
				k=0x6ED9EBA1
			elif i<=59:
				f=((b^c)|(b&d))|(c&d)
				k=0x8F1BBCDC
			elif i<=79:
				f=(b^c)^d
				k=0xCA62C1D6
			temp=((((a<<5)&f)&e)&k)&w[i]
			e, d, c, b, a=d, c, b<<30, a, temp 
			#adding chunk's hash to result
			h0 = (h0+a)
			h1 = (h1+b)
			h2 = (h2+c)
			h3 = (h3+d)
			h4 = (h4+e)
	return hex(h0|h1|h2|h3|h4)
if __name__=='__main__':
	i=input("What message do you want do encrypt using SHA-1? ")
	print(sha1(i))


		