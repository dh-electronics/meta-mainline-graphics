SUMMARY = "Apitrace"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aeb969185a143c3c25130bc2c3ef9a50"

SRC_URI = " \
	git://github.com/apitrace/apitrace.git;branch=master \
	file://0001-cmake-Fix-Waffle-detection.patch \
	"
UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "4a2d21fe836876e95012ffeb682010aef852b97d"
PV = "9.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake pkgconfig python3native ${@'features_check' if (d.getVar('LAYERSERIES_CORENAMES') not in ["zeus"]) else 'distro_features_check'}

DEPENDS += " waffle procps libpng zlib "

# depends on virtual/libgl
REQUIRED_DISTRO_FEATURES += "opengl"

OECMAKE_GENERATOR = "Ninja"
EXTRA_OECMAKE = " \
	-DCMAKE_BUILD_TYPE=Release -DENABLE_GUI=OFF -DENABLE_WAFFLE=ON \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', '-DENABLE_X11=ON', '-DENABLE_X11=OFF', d)} \
	"
