SUMMARY = "Apitrace"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aeb969185a143c3c25130bc2c3ef9a50"

SRC_URI = " \
	git://github.com/apitrace/apitrace.git;branch=master \
	file://0001-cmake-Fix-Waffle-detection.patch \
	"
UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "d5861c63e070d88ccd2fecaa440cc9cace1c48b6"
PV = "9.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake pkgconfig python3native distro_features_check

DEPENDS += " waffle procps libpng zlib "

# depends on virtual/libgl
REQUIRED_DISTRO_FEATURES += "opengl"

OECMAKE_GENERATOR = "Ninja"
EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=Release -DENABLE_GUI=OFF -DENABLE_X11=OFF -DENABLE_WAFFLE=ON "
