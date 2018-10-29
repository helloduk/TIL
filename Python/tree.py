import glob, os.path

def traverse(dir, depth) :
	for obj in glob.glob(dir + '/*'):
		if depth==0:
			prefix = '|--'
		else:
			prefix = '|' + ' '*depth + '+--'
		if os.path.isdir(obj):
			print( prefix + os.path.basename(obj) )
			traverse(obj, depth+1)
		elif os.path.isfile(obj):
			print( prefix + os.path.basename(obj) )
		else:
			print( prefix + 'unknown object:', obj)

if __name__=='__main__':
	traverse('.', 0)