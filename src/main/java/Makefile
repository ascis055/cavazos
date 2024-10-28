#
# Makefile for compiling Java project
#
TARGETCLASS = CavazosApp
TARGETCLASSPACKAGE = com.github.ascis055.cavazos
TESTFILE = commands.json

TARGETCLASSPACKAGEPATH = $(subst .,/,$(TARGETCLASSPACKAGE))
TARGET = $(TARGETCLASS).jar
LIBDIRS = . \
	org/json/simple \
	org/json/simple/parser
LIBFILES = $(TARGETCLASSPACKAGEPATH)/JSONFile.class \
	$(foreach dir,$(LIBDIRS),\
	$(patsubst %.java,%.class,$(wildcard $(dir)/*.java)))

all: $(TARGET)

run: $(TARGET)
	java -jar $< $(TESTFILE)

clean:
	$(RM) $(TARGET) \
	$(TARGETCLASSPACKAGEPATH)/*.class \
	$(foreach dir,$(LIBDIRS),$(dir)/*.class) \
	*~ \
	$(TARGETCLASSPACKAGEPATH)/*~ \
	$(foreach dir,$(LIBDIRS),$(dir)/*~)

$(TARGET): $(TARGETCLASSPACKAGEPATH)/$(TARGETCLASS).class $(LIBFILES)
	jar --create --file $@ \
	--main-class $(TARGETCLASSPACKAGE).$(TARGETCLASS) $^

%.class: %.java
	javac $<
