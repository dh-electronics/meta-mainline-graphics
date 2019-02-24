SUMMARY = "GLslang"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=918e668376010a04448a312fb37ae69b"

SRC_URI = " \
	git://github.com/KhronosGroup/glslang.git;branch=master \
	"
UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "c538b5d796fb24dd418fdd650c7f76e56bcc3dd8"
PV = "1.4+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake pkgconfig python3native distro_features_check

# depends on virtual/libgl
REQUIRED_DISTRO_FEATURES += "opengl"

OECMAKE_GENERATOR = "Ninja"
EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=Release -DENABLE_HLSL=TRUE "
