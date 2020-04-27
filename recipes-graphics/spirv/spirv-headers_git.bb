SUMMARY = "SPIRV-Headers"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c938b85bceb8fb26c1a807f28a52ae2d"

SRC_URI = " \
	git://github.com/KhronosGroup/SPIRV-Headers.git;protocol=https \
	"
UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "e4322e3be589e1ddd44afb20ea842a977c1319b8"
PV = "1.4.2+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake pkgconfig python3native ${@'features_check' if (d.getVar('LAYERSERIES_CORENAMES') not in ["zeus"]) else 'distro_features_check'}

# depends on virtual/libgl
REQUIRED_DISTRO_FEATURES += "opengl"

OECMAKE_GENERATOR = "Ninja"
