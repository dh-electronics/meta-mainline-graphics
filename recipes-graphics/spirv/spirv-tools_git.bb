SUMMARY = "SPIRV-Tools"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c938b85bceb8fb26c1a807f28a52ae2d"

SRC_URI = " \
	git://github.com/KhronosGroup/SPIRV-Tools.git;protocol=https \
	"
UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "1eb89172a82b436d8037e8a8c29c80f7e1f7df74"
PV = "2019.4+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS = "spirv-headers"

inherit cmake pkgconfig python3native distro_features_check

# depends on virtual/libgl
REQUIRED_DISTRO_FEATURES += "opengl"

OECMAKE_GENERATOR = "Ninja"
