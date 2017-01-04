#import urllib
import urllib2
import json
import os

GAMEVER = '1.11'

def getLatestForge(game):
    #download the versions file
    #urllib.urlretrieve("http://files.minecraftforge.net/maven/net/minecraftforge/forge/json","forgeVersions.json")
    #versionFile = open("forgeVersions.json","r")

    #download the versions
    j = urllib2.urlopen("http://files.minecraftforge.net/maven/net/minecraftforge/forge/json")
    #load as JSON
    j_obj = json.load(j)

    b19 = j_obj['mcversion'][game]
    ver = b19[len(b19)-1]
    version = str(ver)

    build = j_obj['number'][version]
    mc = build['mcversion']
    branch = build['branch']
    num = build['version']

    gradleVer = mc+"-"+num+("-"+branch if branch!=None else "")
    return gradleVer

if __name__=='__main__':
    latest = getLatestForge(GAMEVER)
    if os.path.isfile("forgeversion.txt") and not os.environ.get('CI') :
        with open("forgeVersion.txt","r") as f:
            old = f.read()
        if(old!=latest and os.path.isfile("hookURL.txt")):
            #there was an update, trigger the build
            with open("hookURL.txt","r") as f:
                hook = f.read()
            urllib2.urlopen(hook)
    #else:
        #drone thing
        #os.environ.set('')
        #we'll let gradle handle this for now
            
    #save the newest version
    with open("forgeversion.txt","w") as f:
        f.write(latest)
