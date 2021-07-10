/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * Author: Le Viet Man
 * Email: manleviet@gmail.com
 */

package at.tugraz.ist.ase.knowledgebases;

import at.tugraz.ist.ase.knowledgebases.core.KB;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class RenaultKB implements KB {

    Model modelKB = new Model("RenaultConfigurationProblem");
    int numberOfVariables = 99;
    IntVar[] vars = new IntVar[numberOfVariables];

    int[][] domains = new int [numberOfVariables][];
    int[] domainSizes = new int[numberOfVariables];

    List<Constraint> unaryConstraints;

    public RenaultKB(){
        defineVariableValues();
        defineVariables();
        defineConstraints();
    }

    public void defineVariableValues() {
        int i=0;
        // 1
        // Var1 : B64, D64, E64, F64, J64, K25, L64, S64, V25
        domains[i]=new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        domainSizes[i++] = 9;
        // 2
        // Var2 : E0, E1, E2, E3, E5
        domains[i]=new int[]{0, 1, 2, 3, 4};
        domainSizes[i++] = 5;
        // 3
        // Var3 : M5, M6, M7, M8, M9, MA, MB, MC, MD, ME, MF, MG, MH, MJ, MK, ML, MM, MN, MS, MT, MU, MY, ND1G, NM0C, NM2K
        domains[i]=new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        domainSizes[i++] = 25;
        // 4
        // Var4 : DIESEL, ESS
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 5
        // Var5 : AFSU, ALLE, ARGE, AUST, AUTR, BELG, BRES, CETI, CHIL, COLO, DAIB, DAIC, DAID, DAIF, DANE, DOTO, ESPA, EUOR, FINL, FRAN, GRBR, GREC, HOLL, HONG, IRLA, ISLA, ISRA, ITAL, JAPO, MAGH, MARO, NORV, POLO, PORT, SLVQ, SUED, SUIS, TAIW, TCHE, TURQ, URUG, YOUG
        domains[i]=new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41};
        domainSizes[i++] = 42;
        // 6
        // Var6 : DD, DG
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 7
        // Var7 : GDFROI, TEMP
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 8
        // Var8 : CPLG, CPLN
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 9
        // Var9 : DA, DM
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 10
        // Var10 : SANCOA
        domains[i]=new int[]{0};
        domainSizes[i++] = 1;
        // 11
        // Var11 : ABS, SSABS
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 12
        // Var12 : GALERI, SGALER
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 13
        // Var13 : CA, CHAUFO
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 14
        // Var14 : CAPVMA, CATOEL, CATOMA, TN, TO, TODEGO
        domains[i]=new int[]{0, 1, 2, 3, 4, 5};
        domainSizes[i++] = 6;
        // 15
        // Var15 : PBCH, PBNCH
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 16
        // Var16 : VC, VT
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 17
        // Var17 : SPRTEL
        domains[i]=new int[]{0};
        domainSizes[i++] = 1;
        // 18
        // Var18 : ELA, SSELA
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 19
        // Var19 : CPE, SSCPE
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 20
        // Var20 : SSTIR, TIR
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 21
        // Var21 : RETROE, RETROR
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 22
        // Var22 : REGSIT, SSRSIT
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 23
        // Var23 : PROJAB, SPROJA
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 24
        // Var24 : SSARCE
        domains[i]=new int[]{0};
        domainSizes[i++] = 1;
        // 25
        // Var25 : CUSFIX, CUSPIV, SANCUS
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 26
        // Var26 : CLB, CLCGRI, SANCL
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 27
        // Var27 : ADAC, SADAC
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 28
        // Var28 : CCHBAG, SCCHBA
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 29
        // Var29 : CUI, DRA
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 30
        // Var30 : ATARPH, CRIT3ATRPH, SSATAR
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 31
        // Var31 : BANAR, CRIT3SJAR, CRIT3SJARI, FBANAR, FBARAC, SBANAR
        domains[i]=new int[]{0, 1, 2, 3, 4, 5};
        domainSizes[i++] = 6;
        // 32
        // Var32 : SGMANU, SGMEMO
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 33
        // Var33 : KM, MILES
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 34
        // Var34 : Autre167, CPK01, CPK02
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 35
        // Var35 : RIDOAR, SRIDAR
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 36
        // Var36 : PTCAV
        domains[i]=new int[]{0};
        domainSizes[i++] = 1;
        // 37
        // Var39 : CATADI, SCATAD
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 38
        // Var40 : EMBNOR, EMBPIL, SEMBRY
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 39
        // Var41 : PNERFL, PNESTD
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 40
        // Var42 : PHAN01, PHAN02, SSPHAN
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 41
        // Var43 : ETPN01, ETPN02, SSETPN
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 42
        // Var44 : Autre272, EQGPL, PREGPL
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 43
        // Var45 : SUSNOR, SUSREN
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 44
        // Var46 : RENTC, RETC
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 45
        // Var47 : LVAVEL, LVAVMA
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 46
        // Var48 : LVAREL, LVARMA, SSLVAR
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 47
        // Var49 : Autre310, SSAMVA
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 48
        // Var50 : SASURV
        domains[i]=new int[]{0};
        domainSizes[i++] = 1;
        // 49
        // Var51 : SGACHA, SGSCHA
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 50
        // Var52 : COFIXE, COLOMB, COREHA, CORHLO
        domains[i]=new int[]{0, 1, 2, 3};
        domainSizes[i++] = 4;
        // 51
        // Var53 : AVPFIL, SSPFIL
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 52
        // Var54 : NAVIG, SNAVIG
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 53
        // Var55 : CRIT2RHENF, RHENF, SRHENF
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 54
        // Var56 : APL01, APL02
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 55
        // Var57 : AILAR, SAILAR
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 56
        // Var58 : MGMAZE, MGMECO, MGMINI, MGMRNA, MGMRNE, MGMRTA, MGMRTE, MGMRXT, RL, RN, RT, RXE, SMONEQ
        domains[i]=new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        domainSizes[i++] = 13;
        // 57
        // Var59 : RUNLI, SRUNLI
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 58
        // Var60 : LAVPH, SLAVPH
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 59
        // Var61 : EVCFIX, EVCVAR
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 60
        // Var62 : AOEF, SAOEF
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 61
        // Var63 : VOLNRH, VOLRH
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 62
        // Var64 : PANP00, PANP01, PANP02, PANP03, PANP05
        domains[i]=new int[]{0, 1, 2, 3, 4};
        domainSizes[i++] = 5;
        // 63
        // Var65 : PLAFNT, PLAFT
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 64
        // Var66 : JANALU, JANTOL
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 65
        // Var67 : T0, T1, T2, T3, T5
        domains[i]=new int[]{0, 1, 2, 3, 4};
        domainSizes[i++] = 5;
        // 66
        // Var68 : PSCOMI, PSCOMR, PSCOPO
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 67
        // Var69 : PSPAMI, PSPAMR
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 68
        // Var70 : ETAP01, ETAP03, SSETAP
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 69
        // Var71 : FSTPO
        domains[i]=new int[]{0};
        domainSizes[i++] = 1;
        // 70
        // Var72 : DUCA, FUJI, KANG, ODIN, PARALL, PARBRE, PARGBR, PARPOL, PARTCH, SSEDNC, VRMI
        domains[i]=new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        domainSizes[i++] = 11;
        // 71
        // Var73 : TBOR00, TBOR01, TBOR02, TBOR03, TBOR05
        domains[i]=new int[]{0, 1, 2, 3, 4};
        domainSizes[i++] = 5;
        // 72
        // Var74 : PBOR00, PBOR01, PBOR02, PBOR03, PBOR05
        domains[i]=new int[]{0, 1, 2, 3, 4};
        domainSizes[i++] = 5;
        // 73
        // Var75 : MOCY01, MOCY02, MOCY03, MOCY04, MOCY05, MOCY06, MOCY07, MOCY08, MOCY10, MOCY11, MOCY12, SSMOCY
        domains[i]=new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        domainSizes[i++] = 12;
        // 74
        // Var76 : Autre408, NINAV1
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 75
        // Var77 : ABPA01, SSABPA
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 76
        // Var78 : FIPOU, SFIPOU
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 77
        // Var79 : ABCO01, SSABCO
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 78
        // Var80 : ANSRAD, CRIT2X5KS, CRIT2X8KI, CRIT4X15CI, CRIT4X15KI, CRIT4X25KI, SRADIO
        domains[i]=new int[]{0, 1, 2, 3, 4, 5, 6};
        domainSizes[i++] = 7;
        // 79
        // Var81 : BVA4, BVM5
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 80
        // Var82 : NMAS01, NMAS02, NMAS03, NMAS04, NMAS05
        domains[i]=new int[]{0, 1, 2, 3, 4};
        domainSizes[i++] = 5;
        // 81
        // Var83 : Autre432, PLARPT, PLARVN, PLARVO
        domains[i]=new int[]{0, 1, 2, 3};
        domainSizes[i++] = 4;
        // 82
        // Var84 : ECLAR, SECLAR
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 83
        // Var85 : CDCOF, SCDCOF
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 84
        // Var86 : ACPLAR, SACPLA
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 85
        // Var87 : MONORM, SURMO1
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 86
        // Var88 : Autre497, JANDIF
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 87
        // Var89 : Autre513, EVA, EVE
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 88
        // Var90 : ANTID, ANTIDI, SDPCLV
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 89
        // Var91 : Autre613, TKO
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 90
        // Var92 : Autre713, CPNT01, CPNT02, CPNT03, EQDIF
        domains[i]=new int[]{0, 1, 2, 3, 4};
        domainSizes[i++] = 5;
        // 91
        // Var93 : Autre714, BVDIF
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 92
        // Var94 : CRIT1503, EU00, EU93, EU96
        domains[i]=new int[]{0, 1, 2, 3};
        domainSizes[i++] = 4;
        // 93
        // Var95 : CRIT1149CC, CRIT1390CC, CRIT1598CC, CRIT1870CC, CRIT1998CC
        domains[i]=new int[]{0, 1, 2, 3, 4};
        domainSizes[i++] = 5;
        // 94
        // Var96 : CRIT060CV, CRIT065CV, CRIT070CV, CRIT075CV, CRIT090CV, CRIT095CV, CRIT100CV, CRIT105CV, CRIT115CV, CRIT150CV
        domains[i]=new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        domainSizes[i++] = 10;
        // 95
        // Var97 : BCNTC, BCTC
        domains[i]=new int[]{0, 1};
        domainSizes[i++] = 2;
        // 96
        // Var98 : AZE, Autre913, CPTECO
        domains[i]=new int[]{0, 1, 2};
        domainSizes[i++] = 3;
        // 97
        // Var99 : AD4, DP0, JB1, JB3, JC5
        domains[i]=new int[]{0, 1, 2, 3, 4};
        domainSizes[i++] = 5;
        // 98
        // Var100 : CRIT620, CRIT622, CRIT624, CRIT700, CRIT701, CRIT702, CRIT703, CRIT710, CRIT714, CRIT720, CRIT730, CRIT731, CRIT732, CRIT733, CRIT734, CRIT736, CRIT740, CRIT750, CRIT751, CRIT752, CRIT764, CRIT784, CRIT786, CRIT788, CRIT790, CRIT791, CRIT796, CRIT797, CRIT798
        domains[i]=new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
        domainSizes[i++] = 29;
        // 99
        // Var101 : D7F, E7J, F3R, F4R, F7R, F8Q, F9Q, K4J, K4M, K7M
        domains[i]=new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        domainSizes[i] = 10;
    }

    public void defineVariables (){
        int i = 0;
        vars[0] = this.modelKB.intVar("Var1", domains[i++]);
        vars[1] = this.modelKB.intVar("Var2", domains[i++]);
        vars[2] = this.modelKB.intVar("Var3", domains[i++]);
        vars[3] = this.modelKB.intVar("Var4", domains[i++]);
        vars[4] = this.modelKB.intVar("Var5", domains[i++]);
        vars[5] = this.modelKB.intVar("Var6", domains[i++]);
        vars[6] = this.modelKB.intVar("Var7", domains[i++]);
        vars[7] = this.modelKB.intVar("Var8", domains[i++]);
        vars[8] = this.modelKB.intVar("Var9", domains[i++]);
        vars[9] = this.modelKB.intVar("Var10", domains[i++]);
        vars[10] = this.modelKB.intVar("Var11", domains[i++]);
        vars[11] = this.modelKB.intVar("Var12", domains[i++]);
        vars[12] = this.modelKB.intVar("Var13", domains[i++]);
        vars[13] = this.modelKB.intVar("Var14", domains[i++]);
        vars[14] = this.modelKB.intVar("Var15", domains[i++]);
        vars[15] = this.modelKB.intVar("Var16",domains[i++]);
        vars[16] = this.modelKB.intVar("Var17", domains[i++]);
        vars[17] = this.modelKB.intVar("Var18",domains[i++]);
        vars[18] = this.modelKB.intVar("Var19", domains[i++]);
        vars[19] = this.modelKB.intVar("Var20", domains[i++]);
        vars[20] = this.modelKB.intVar("Var21", domains[i++]);
        vars[21] = this.modelKB.intVar("Var22", domains[i++]);
        vars[22] = this.modelKB.intVar("Var23", domains[i++]);
        vars[23] = this.modelKB.intVar("Var24",domains[i++]);
        vars[24] = this.modelKB.intVar("Var25", domains[i++]);
        vars[25] = this.modelKB.intVar("Var26", domains[i++]);
        vars[26] = this.modelKB.intVar("Var27", domains[i++]);
        vars[27] = this.modelKB.intVar("Var28", domains[i++]);
        vars[28] = this.modelKB.intVar("Var29", domains[i++]);
        vars[29] = this.modelKB.intVar("Var30", domains[i++]);
        vars[30] = this.modelKB.intVar("Var31", domains[i++]);
        vars[31] = this.modelKB.intVar("Var32",domains[i++]);
        vars[32] = this.modelKB.intVar("Var33",domains[i++]);
        vars[33] = this.modelKB.intVar("Var34", domains[i++]);
        vars[34] = this.modelKB.intVar("Var35", domains[i++]);
        vars[35] = this.modelKB.intVar("Var36", domains[i++]);
        vars[36] = this.modelKB.intVar("Var39", domains[i++]);
        vars[37] = this.modelKB.intVar("Var40",domains[i++]);
        vars[38] = this.modelKB.intVar("Var41", domains[i++]);
        vars[39] = this.modelKB.intVar("Var42", domains[i++]);
        vars[40] = this.modelKB.intVar("Var43", domains[i++]);
        vars[41] = this.modelKB.intVar("Var44",domains[i++]);
        vars[42] = this.modelKB.intVar("Var45", domains[i++]);
        vars[43] = this.modelKB.intVar("Var46", domains[i++]);
        vars[44] = this.modelKB.intVar("Var47", domains[i++]);
        vars[45] = this.modelKB.intVar("Var48",domains[i++]);
        vars[46] = this.modelKB.intVar("Var49", domains[i++]);
        vars[47] = this.modelKB.intVar("Var50", domains[i++]);
        vars[48] = this.modelKB.intVar("Var51", domains[i++]);
        vars[49] = this.modelKB.intVar("Var52",domains[i++]);
        vars[50] = this.modelKB.intVar("Var53", domains[i++]);
        vars[51] = this.modelKB.intVar("Var54", domains[i++]);
        vars[52] = this.modelKB.intVar("Var55", domains[i++]);
        vars[53] = this.modelKB.intVar("Var56",domains[i++]);
        vars[54] = this.modelKB.intVar("Var57", domains[i++]);
        vars[55] = this.modelKB.intVar("Var58", domains[i++]);
        vars[56] = this.modelKB.intVar("Var59", domains[i++]);
        vars[57] = this.modelKB.intVar("Var60",domains[i++]);
        vars[58] = this.modelKB.intVar("Var61", domains[i++]);
        vars[59] = this.modelKB.intVar("Var62", domains[i++]);
        vars[60] = this.modelKB.intVar("Var63", domains[i++]);
        vars[61] = this.modelKB.intVar("Var64",domains[i++]);
        vars[62] = this.modelKB.intVar("Var65", domains[i++]);
        vars[63] = this.modelKB.intVar("Var66", domains[i++]);
        vars[64] = this.modelKB.intVar("Var67", domains[i++]);
        vars[65] = this.modelKB.intVar("Var68",domains[i++]);
        vars[66] = this.modelKB.intVar("Var69", domains[i++]);
        vars[67] = this.modelKB.intVar("Var70", domains[i++]);
        vars[68] = this.modelKB.intVar("Var71", domains[i++]);
        vars[69] = this.modelKB.intVar("Var72", domains[i++]);
        vars[70] = this.modelKB.intVar("Var73",domains[i++]);
        vars[71] = this.modelKB.intVar("Var74", domains[i++]);
        vars[72] = this.modelKB.intVar("Var75", domains[i++]);
        vars[73] = this.modelKB.intVar("Var76", domains[i++]);
        vars[74] = this.modelKB.intVar("Var77", domains[i++]);
        vars[75] = this.modelKB.intVar("Var78",domains[i++]);
        vars[76] = this.modelKB.intVar("Var79", domains[i++]);
        vars[77] = this.modelKB.intVar("Var80", domains[i++]);
        vars[78] = this.modelKB.intVar("Var81", domains[i++]);
        vars[79] = this.modelKB.intVar("Var82", domains[i++]);
        vars[80] = this.modelKB.intVar("Var83",domains[i++]);
        vars[81] = this.modelKB.intVar("Var84", domains[i++]);
        vars[82] = this.modelKB.intVar("Var85", domains[i++]);
        vars[83] = this.modelKB.intVar("Var86", domains[i++]);
        vars[84] = this.modelKB.intVar("Var87", domains[i++]);
        vars[85] = this.modelKB.intVar("Var88",domains[i++]);
        vars[86] = this.modelKB.intVar("Var89", domains[i++]);
        vars[87] = this.modelKB.intVar("Var90", domains[i++]);
        vars[88] = this.modelKB.intVar("Var91", domains[i++]);
        vars[89] = this.modelKB.intVar("Var92", domains[i++]);
        vars[90] = this.modelKB.intVar("Var93",domains[i++]);
        vars[91] = this.modelKB.intVar("Var94", domains[i++]);
        vars[92] = this.modelKB.intVar("Var95", domains[i++]);
        vars[93] = this.modelKB.intVar("Var96", domains[i++]);
        vars[94] = this.modelKB.intVar("Var97", domains[i++]);
        vars[95] = this.modelKB.intVar("Var98",domains[i++]);
        vars[96] = this.modelKB.intVar("Var99", domains[i++]);
        vars[97] = this.modelKB.intVar("Var100", domains[i++]);
        vars[98] = this.modelKB.intVar("Var101", domains[i++]);
    }

    public void defineConstraints() {
        unaryConstraints = defineUnaryConstraints();

        // read all files from a resources folder
        try {

            // files from src/main/resources/renault_rules
            List<File> result = getAllFilesFromResource("renault_rules");
            for (File file : result) {
                if (file.getName().contains(".pm")) {
                    System.out.println("Reading " + file.getName());
                    readRule(file);
                }

//                System.out.println("file : " + file);
//                printFile(file);
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

//        getClass().getResource()
//        String path = getClass().getResource("renault_rules").toString();
//        File folder = new File("renault_rules");
//
//        Files.
//
//        Files.exists(new Path("renault_rules"));
//
//        for (final File file : folder.listFiles()) {
//            if (file.getName().contains(".pm")) {
//                System.out.println("Reading " + file.getName());
//                readRule(file);
//            }
//        }
    }

    private List<File> getAllFilesFromResource(String folder)
            throws URISyntaxException, IOException {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(folder);

        // dun walk the root path, we will walk all the classes
        List<File> collect = Files.walk(Paths.get(resource.toURI()))
                                            .filter(Files::isRegularFile)
                                            .map(x -> x.toFile())
                                            .collect(Collectors.toList());

        return collect;
    }

    private void readRule(File file) {
        BufferedReader reader;
        try {

            // Open file
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine(); // ignore

            Constraint constraint = or(reader);
            constraint.post();

            // Close file
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Constraint or(BufferedReader reader) {
        List<Constraint> constraints = new ArrayList<>();
        try {
            String line = reader.readLine();
//            System.out.println(line);

            while (!line.equals("or)")) {
                if (line.equals("or(")) {
                    constraints.add(or(reader));
                } else {
                    constraints.add(aLine(line));
                }

                line = reader.readLine();
//                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Constraint[] arrConstraints = constraints.toArray(new Constraint[0]);
        return modelKB.or(arrConstraints);
    }

    private Constraint aLine(String line) {
        String[] tokens = line.split(" and ");

        List<Constraint> constraints = new ArrayList<>();
        for (String token : tokens) {
            String[] var_val = token.split(" = ");

            String variable = var_val[0].trim();
            String value = var_val[1].trim();

            int idxUnaryConstraint = getIndexUnaryConstraint(variable + " = " + value);

            if (idxUnaryConstraint != -1) {
                constraints.add(unaryConstraints.get(idxUnaryConstraint));
            } else {
                System.out.println(variable + " = " + value + " TRAT BAY ROI");
            }
        }

        Constraint[] arrConstraints = constraints.toArray(new Constraint[0]);
        return modelKB.and(arrConstraints);
    }

    private Integer getIndexUnaryConstraint(String token) {
        Map<String, Integer> map = new HashMap<>();

        map.put("Var1 = B64", 0);
        map.put("Var1 = D64", 1);
        map.put("Var1 = E64", 2);
        map.put("Var1 = F64", 3);
        map.put("Var1 = J64", 4);
        map.put("Var1 = K25", 5);
        map.put("Var1 = L64", 6);
        map.put("Var1 = S64", 7);
        map.put("Var1 = V25", 8);
        map.put("Var2 = E0", 9);
        map.put("Var2 = E1", 10);
        map.put("Var2 = E2", 11);
        map.put("Var2 = E3", 12);
        map.put("Var2 = E5", 13);
        map.put("Var3 = M5", 14);
        map.put("Var3 = M6", 15);
        map.put("Var3 = M7", 16);
        map.put("Var3 = M8", 17);
        map.put("Var3 = M9", 18);
        map.put("Var3 = MA", 19);
        map.put("Var3 = MB", 20);
        map.put("Var3 = MC", 21);
        map.put("Var3 = MD", 22);
        map.put("Var3 = ME", 23);
        map.put("Var3 = MF", 24);
        map.put("Var3 = MG", 25);
        map.put("Var3 = MH", 26);
        map.put("Var3 = MJ", 27);
        map.put("Var3 = MK", 28);
        map.put("Var3 = ML", 29);
        map.put("Var3 = MM", 30);
        map.put("Var3 = MN", 31);
        map.put("Var3 = MS", 32);
        map.put("Var3 = MT", 33);
        map.put("Var3 = MU", 34);
        map.put("Var3 = MY", 35);
        map.put("Var3 = ND1G", 36);
        map.put("Var3 = NM0C", 37);
        map.put("Var3 = NM2K", 38);
        map.put("Var4 = DIESEL", 39);
        map.put("Var4 = ESS", 40);
        map.put("Var5 = AFSU", 41);
        map.put("Var5 = ALLE", 42);
        map.put("Var5 = ARGE", 43);
        map.put("Var5 = AUST", 44);
        map.put("Var5 = AUTR", 45);
        map.put("Var5 = BELG", 46);
        map.put("Var5 = BRES", 47);
        map.put("Var5 = CETI", 48);
        map.put("Var5 = CHIL", 49);
        map.put("Var5 = COLO", 50);
        map.put("Var5 = DAIB", 51);
        map.put("Var5 = DAIC", 52);
        map.put("Var5 = DAID", 53);
        map.put("Var5 = DAIF", 54);
        map.put("Var5 = DANE", 55);
        map.put("Var5 = DOTO", 56);
        map.put("Var5 = ESPA", 57);
        map.put("Var5 = EUOR", 58);
        map.put("Var5 = FINL", 59);
        map.put("Var5 = FRAN", 60);
        map.put("Var5 = GRBR", 61);
        map.put("Var5 = GREC", 62);
        map.put("Var5 = HOLL", 63);
        map.put("Var5 = HONG", 64);
        map.put("Var5 = IRLA", 65);
        map.put("Var5 = ISLA", 66);
        map.put("Var5 = ISRA", 67);
        map.put("Var5 = ITAL", 68);
        map.put("Var5 = JAPO", 69);
        map.put("Var5 = MAGH", 70);
        map.put("Var5 = MARO", 71);
        map.put("Var5 = NORV", 72);
        map.put("Var5 = POLO", 73);
        map.put("Var5 = PORT", 74);
        map.put("Var5 = SLVQ", 75);
        map.put("Var5 = SUED", 76);
        map.put("Var5 = SUIS", 77);
        map.put("Var5 = TAIW", 78);
        map.put("Var5 = TCHE", 79);
        map.put("Var5 = TURQ", 80);
        map.put("Var5 = URUG", 81);
        map.put("Var5 = YOUG", 82);
        map.put("Var6 = DD", 83);
        map.put("Var6 = DG", 84);
        map.put("Var7 = GDFROI", 85);
        map.put("Var7 = TEMP", 86);
        map.put("Var8 = CPLG", 87);
        map.put("Var8 = CPLN", 88);
        map.put("Var9 = DA", 89);
        map.put("Var9 = DM", 90);
        map.put("Var10 = SANCOA", 91);
        map.put("Var11 = ABS", 92);
        map.put("Var11 = SSABS", 93);
        map.put("Var12 = GALERI", 94);
        map.put("Var12 = SGALER", 95);
        map.put("Var13 = CA", 96);
        map.put("Var13 = CHAUFO", 97);
        map.put("Var14 = CAPVMA", 98);
        map.put("Var14 = CATOEL", 99);
        map.put("Var14 = CATOMA", 100);
        map.put("Var14 = TN", 101);
        map.put("Var14 = TO", 102);
        map.put("Var14 = TODEGO", 103);
        map.put("Var15 = PBCH", 104);
        map.put("Var15 = PBNCH", 105);
        map.put("Var16 = VC", 106);
        map.put("Var16 = VT", 107);
        map.put("Var17 = SPRTEL", 108);
        map.put("Var18 = ELA", 109);
        map.put("Var18 = SSELA", 110);
        map.put("Var19 = CPE", 111);
        map.put("Var19 = SSCPE", 112);
        map.put("Var20 = SSTIR", 113);
        map.put("Var20 = TIR", 114);
        map.put("Var21 = RETROE", 115);
        map.put("Var21 = RETROR", 116);
        map.put("Var22 = REGSIT", 117);
        map.put("Var22 = SSRSIT", 118);
        map.put("Var23 = PROJAB", 119);
        map.put("Var23 = SPROJA", 120);
        map.put("Var24 = SSARCE", 121);
        map.put("Var25 = CUSFIX", 122);
        map.put("Var25 = CUSPIV", 123);
        map.put("Var25 = SANCUS", 124);
        map.put("Var26 = CLB", 125);
        map.put("Var26 = CLCGRI", 126);
        map.put("Var26 = SANCL", 127);
        map.put("Var27 = ADAC", 128);
        map.put("Var27 = SADAC", 129);
        map.put("Var28 = CCHBAG", 130);
        map.put("Var28 = SCCHBA", 131);
        map.put("Var29 = CUI", 132);
        map.put("Var29 = DRA", 133);
        map.put("Var30 = ATARPH", 134);
        map.put("Var30 = CRIT3ATRPH", 135);
        map.put("Var30 = SSATAR", 136);
        map.put("Var31 = BANAR", 137);
        map.put("Var31 = CRIT3SJAR", 138);
        map.put("Var31 = CRIT3SJARI", 139);
        map.put("Var31 = FBANAR", 140);
        map.put("Var31 = FBARAC", 141);
        map.put("Var31 = SBANAR", 142);
        map.put("Var32 = SGMANU", 143);
        map.put("Var32 = SGMEMO", 144);
        map.put("Var33 = KM", 145);
        map.put("Var33 = MILES", 146);
        map.put("Var34 = Autre167", 147);
        map.put("Var34 = CPK01", 148);
        map.put("Var34 = CPK02", 149);
        map.put("Var35 = RIDOAR", 150);
        map.put("Var35 = SRIDAR", 151);
        map.put("Var36 = PTCAV", 152);
        map.put("Var39 = CATADI", 153);
        map.put("Var39 = SCATAD", 154);
        map.put("Var40 = EMBNOR", 155);
        map.put("Var40 = EMBPIL", 156);
        map.put("Var40 = SEMBRY", 157);
        map.put("Var41 = PNERFL", 158);
        map.put("Var41 = PNESTD", 159);
        map.put("Var42 = PHAN01", 160);
        map.put("Var42 = PHAN02", 161);
        map.put("Var42 = SSPHAN", 162);
        map.put("Var43 = ETPN01", 163);
        map.put("Var43 = ETPN02", 164);
        map.put("Var43 = SSETPN", 165);
        map.put("Var44 = Autre272", 166);
        map.put("Var44 = EQGPL", 167);
        map.put("Var44 = PREGPL", 168);
        map.put("Var45 = SUSNOR", 169);
        map.put("Var45 = SUSREN", 170);
        map.put("Var46 = RENTC", 171);
        map.put("Var46 = RETC", 172);
        map.put("Var47 = LVAVEL", 173);
        map.put("Var47 = LVAVMA", 174);
        map.put("Var48 = LVAREL", 175);
        map.put("Var48 = LVARMA", 176);
        map.put("Var48 = SSLVAR", 177);
        map.put("Var49 = Autre310", 178);
        map.put("Var49 = SSAMVA", 179);
        map.put("Var50 = SASURV", 180);
        map.put("Var51 = SGACHA", 181);
        map.put("Var51 = SGSCHA", 182);
        map.put("Var52 = COFIXE", 183);
        map.put("Var52 = COLOMB", 184);
        map.put("Var52 = COREHA", 185);
        map.put("Var52 = CORHLO", 186);
        map.put("Var53 = AVPFIL", 187);
        map.put("Var53 = SSPFIL", 188);
        map.put("Var54 = NAVIG", 189);
        map.put("Var54 = SNAVIG", 190);
        map.put("Var55 = CRIT2RHENF", 191);
        map.put("Var55 = RHENF", 192);
        map.put("Var55 = SRHENF", 193);
        map.put("Var56 = APL01", 194);
        map.put("Var56 = APL02", 195);
        map.put("Var57 = AILAR", 196);
        map.put("Var57 = SAILAR", 197);
        map.put("Var58 = MGMAZE", 198);
        map.put("Var58 = MGMECO", 199);
        map.put("Var58 = MGMINI", 200);
        map.put("Var58 = MGMRNA", 201);
        map.put("Var58 = MGMRNE", 202);
        map.put("Var58 = MGMRTA", 203);
        map.put("Var58 = MGMRTE", 204);
        map.put("Var58 = MGMRXT", 205);
        map.put("Var58 = RL", 206);
        map.put("Var58 = RN", 207);
        map.put("Var58 = RT", 208);
        map.put("Var58 = RXE", 209);
        map.put("Var58 = SMONEQ", 210);
        map.put("Var59 = RUNLI", 211);
        map.put("Var59 = SRUNLI", 212);
        map.put("Var60 = LAVPH", 213);
        map.put("Var60 = SLAVPH", 214);
        map.put("Var61 = EVCFIX", 215);
        map.put("Var61 = EVCVAR", 216);
        map.put("Var62 = AOEF", 217);
        map.put("Var62 = SAOEF", 218);
        map.put("Var63 = VOLNRH", 219);
        map.put("Var63 = VOLRH", 220);
        map.put("Var64 = PANP00", 221);
        map.put("Var64 = PANP01", 222);
        map.put("Var64 = PANP02", 223);
        map.put("Var64 = PANP03", 224);
        map.put("Var64 = PANP05", 225);
        map.put("Var65 = PLAFNT", 226);
        map.put("Var65 = PLAFT", 227);
        map.put("Var66 = JANALU", 228);
        map.put("Var66 = JANTOL", 229);
        map.put("Var67 = T0", 230);
        map.put("Var67 = T1", 231);
        map.put("Var67 = T2", 232);
        map.put("Var67 = T3", 233);
        map.put("Var67 = T5", 234);
        map.put("Var68 = PSCOMI", 235);
        map.put("Var68 = PSCOMR", 236);
        map.put("Var68 = PSCOPO", 237);
        map.put("Var69 = PSPAMI", 238);
        map.put("Var69 = PSPAMR", 239);
        map.put("Var70 = ETAP01", 240);
        map.put("Var70 = ETAP03", 241);
        map.put("Var70 = SSETAP", 242);
        map.put("Var71 = FSTPO", 243);
        map.put("Var72 = DUCA", 244);
        map.put("Var72 = FUJI", 245);
        map.put("Var72 = KANG", 246);
        map.put("Var72 = ODIN", 247);
        map.put("Var72 = PARALL", 248);
        map.put("Var72 = PARBRE", 249);
        map.put("Var72 = PARGBR", 250);
        map.put("Var72 = PARPOL", 251);
        map.put("Var72 = PARTCH", 252);
        map.put("Var72 = SSEDNC", 253);
        map.put("Var72 = VRMI", 254);
        map.put("Var73 = TBOR00", 255);
        map.put("Var73 = TBOR01", 256);
        map.put("Var73 = TBOR02", 257);
        map.put("Var73 = TBOR03", 258);
        map.put("Var73 = TBOR05", 259);
        map.put("Var74 = PBOR00", 260);
        map.put("Var74 = PBOR01", 261);
        map.put("Var74 = PBOR02", 262);
        map.put("Var74 = PBOR03", 263);
        map.put("Var74 = PBOR05", 264);
        map.put("Var75 = MOCY01", 265);
        map.put("Var75 = MOCY02", 266);
        map.put("Var75 = MOCY03", 267);
        map.put("Var75 = MOCY04", 268);
        map.put("Var75 = MOCY05", 269);
        map.put("Var75 = MOCY06", 270);
        map.put("Var75 = MOCY07", 271);
        map.put("Var75 = MOCY08", 272);
        map.put("Var75 = MOCY10", 273);
        map.put("Var75 = MOCY11", 274);
        map.put("Var75 = MOCY12", 275);
        map.put("Var75 = SSMOCY", 276);
        map.put("Var76 = Autre408", 277);
        map.put("Var76 = NINAV1", 278);
        map.put("Var77 = ABPA01", 279);
        map.put("Var77 = SSABPA", 280);
        map.put("Var78 = FIPOU", 281);
        map.put("Var78 = SFIPOU", 282);
        map.put("Var79 = ABCO01", 283);
        map.put("Var79 = SSABCO", 284);
        map.put("Var80 = ANSRAD", 285);
        map.put("Var80 = CRIT2X5KS", 286);
        map.put("Var80 = CRIT2X8KI", 287);
        map.put("Var80 = CRIT4X15CI", 288);
        map.put("Var80 = CRIT4X15KI", 289);
        map.put("Var80 = CRIT4X25KI", 290);
        map.put("Var80 = SRADIO", 291);
        map.put("Var81 = BVA4", 292);
        map.put("Var81 = BVM5", 293);
        map.put("Var82 = NMAS01", 294);
        map.put("Var82 = NMAS02", 295);
        map.put("Var82 = NMAS03", 296);
        map.put("Var82 = NMAS04", 297);
        map.put("Var82 = NMAS05", 298);
        map.put("Var83 = Autre432", 299);
        map.put("Var83 = PLARPT", 300);
        map.put("Var83 = PLARVN", 301);
        map.put("Var83 = PLARVO", 302);
        map.put("Var84 = ECLAR", 303);
        map.put("Var84 = SECLAR", 304);
        map.put("Var85 = CDCOF", 305);
        map.put("Var85 = SCDCOF", 306);
        map.put("Var86 = ACPLAR", 307);
        map.put("Var86 = SACPLA", 308);
        map.put("Var87 = MONORM", 309);
        map.put("Var87 = SURMO1", 310);
        map.put("Var88 = Autre497", 311);
        map.put("Var88 = JANDIF", 312);
        map.put("Var89 = Autre513", 313);
        map.put("Var89 = EVA", 314);
        map.put("Var89 = EVE", 315);
        map.put("Var90 = ANTID", 316);
        map.put("Var90 = ANTIDI", 317);
        map.put("Var90 = SDPCLV", 318);
        map.put("Var91 = Autre613", 319);
        map.put("Var91 = TKO", 320);
        map.put("Var92 = Autre713", 321);
        map.put("Var92 = CPNT01", 322);
        map.put("Var92 = CPNT02", 323);
        map.put("Var92 = CPNT03", 324);
        map.put("Var92 = EQDIF", 325);
        map.put("Var93 = Autre714", 326);
        map.put("Var93 = BVDIF", 327);
        map.put("Var94 = CRIT1503", 328);
        map.put("Var94 = EU00", 329);
        map.put("Var94 = EU93", 330);
        map.put("Var94 = EU96", 331);
        map.put("Var95 = CRIT1149CC", 332);
        map.put("Var95 = CRIT1390CC", 333);
        map.put("Var95 = CRIT1598CC", 334);
        map.put("Var95 = CRIT1870CC", 335);
        map.put("Var95 = CRIT1998CC", 336);
        map.put("Var96 = CRIT060CV", 337);
        map.put("Var96 = CRIT065CV", 338);
        map.put("Var96 = CRIT070CV", 339);
        map.put("Var96 = CRIT075CV", 340);
        map.put("Var96 = CRIT090CV", 341);
        map.put("Var96 = CRIT095CV", 342);
        map.put("Var96 = CRIT100CV", 343);
        map.put("Var96 = CRIT105CV", 344);
        map.put("Var96 = CRIT115CV", 345);
        map.put("Var96 = CRIT150CV", 346);
        map.put("Var97 = BCNTC", 347);
        map.put("Var97 = BCTC", 348);
        map.put("Var98 = AZE", 349);
        map.put("Var98 = Autre913", 350);
        map.put("Var98 = CPTECO", 351);
        map.put("Var99 = AD4", 352);
        map.put("Var99 = DP0", 353);
        map.put("Var99 = JB1", 354);
        map.put("Var99 = JB3", 355);
        map.put("Var99 = JC5", 356);
        map.put("Var100 = CRIT620", 357);
        map.put("Var100 = CRIT622", 358);
        map.put("Var100 = CRIT624", 359);
        map.put("Var100 = CRIT700", 360);
        map.put("Var100 = CRIT701", 361);
        map.put("Var100 = CRIT702", 362);
        map.put("Var100 = CRIT703", 363);
        map.put("Var100 = CRIT710", 364);
        map.put("Var100 = CRIT714", 365);
        map.put("Var100 = CRIT720", 366);
        map.put("Var100 = CRIT730", 367);
        map.put("Var100 = CRIT731", 368);
        map.put("Var100 = CRIT732", 369);
        map.put("Var100 = CRIT733", 370);
        map.put("Var100 = CRIT734", 371);
        map.put("Var100 = CRIT736", 372);
        map.put("Var100 = CRIT740", 373);
        map.put("Var100 = CRIT750", 374);
        map.put("Var100 = CRIT751", 375);
        map.put("Var100 = CRIT752", 376);
        map.put("Var100 = CRIT764", 377);
        map.put("Var100 = CRIT784", 378);
        map.put("Var100 = CRIT786", 379);
        map.put("Var100 = CRIT788", 380);
        map.put("Var100 = CRIT790", 381);
        map.put("Var100 = CRIT791", 382);
        map.put("Var100 = CRIT796", 383);
        map.put("Var100 = CRIT797", 384);
        map.put("Var100 = CRIT798", 385);
        map.put("Var101 = D7F", 386);
        map.put("Var101 = E7J", 387);
        map.put("Var101 = F3R", 388);
        map.put("Var101 = F4R", 389);
        map.put("Var101 = F7R", 390);
        map.put("Var101 = F8Q", 391);
        map.put("Var101 = F9Q", 392);
        map.put("Var101 = K4J", 393);
        map.put("Var101 = K4M", 394);
        map.put("Var101 = K7M", 395);

        return map.getOrDefault(token, -1);
    }

    private List<Constraint> defineUnaryConstraints() {
        List<Constraint> constraints = new ArrayList<>();

        // Var1 : B64, D64, E64, F64, J64, K25, L64, S64, V25
        constraints.add(modelKB.arithm(vars[0], "=", 0)); // Var1 = B64
        constraints.add(modelKB.arithm(vars[0], "=", 1)); // Var1 = D64
        constraints.add(modelKB.arithm(vars[0], "=", 2)); // Var1 = E64
        constraints.add(modelKB.arithm(vars[0], "=", 3)); // Var1 = F64
        constraints.add(modelKB.arithm(vars[0], "=", 4)); // Var1 = J64
        constraints.add(modelKB.arithm(vars[0], "=", 5)); // Var1 = K25
        constraints.add(modelKB.arithm(vars[0], "=", 6)); // Var1 = L64
        constraints.add(modelKB.arithm(vars[0], "=", 7)); // Var1 = S64
        constraints.add(modelKB.arithm(vars[0], "=", 8)); // Var1 = V25

        // Var2 : "E0", "E1", "E2", "E3", "E5"
        constraints.add(modelKB.arithm(vars[1], "=", 0)); // Var2 = E0
        constraints.add(modelKB.arithm(vars[1], "=", 1)); // Var2 = E1
        constraints.add(modelKB.arithm(vars[1], "=", 2)); // Var2 = E2
        constraints.add(modelKB.arithm(vars[1], "=", 3)); // Var2 = E3
        constraints.add(modelKB.arithm(vars[1], "=", 4)); // Var2 = E5

        // Var3 : M5, M6, M7, M8, M9, MA, MB, MC, MD, ME, MF, MG, MH, MJ, MK, ML, MM, MN, MS, MT, MU, MY, ND1G, NM0C, NM2K
        constraints.add(modelKB.arithm(vars[2], "=", 0)); // Var3 = M5
        constraints.add(modelKB.arithm(vars[2], "=", 1)); // Var3 = M6
        constraints.add(modelKB.arithm(vars[2], "=", 2)); // Var3 = M7
        constraints.add(modelKB.arithm(vars[2], "=", 3)); // Var3 = M8
        constraints.add(modelKB.arithm(vars[2], "=", 4)); // Var3 = M9
        constraints.add(modelKB.arithm(vars[2], "=", 5)); // Var3 = MA
        constraints.add(modelKB.arithm(vars[2], "=", 6)); // Var3 = MB
        constraints.add(modelKB.arithm(vars[2], "=", 7)); // Var3 = MC
        constraints.add(modelKB.arithm(vars[2], "=", 8)); // Var3 = MD
        constraints.add(modelKB.arithm(vars[2], "=", 9)); // Var3 = ME
        constraints.add(modelKB.arithm(vars[2], "=", 10)); // Var3 = MF
        constraints.add(modelKB.arithm(vars[2], "=", 11)); // Var3 = MG
        constraints.add(modelKB.arithm(vars[2], "=", 12)); // Var3 = MH
        constraints.add(modelKB.arithm(vars[2], "=", 13)); // Var3 = MJ
        constraints.add(modelKB.arithm(vars[2], "=", 14)); // Var3 = MK
        constraints.add(modelKB.arithm(vars[2], "=", 15)); // Var3 = ML
        constraints.add(modelKB.arithm(vars[2], "=", 16)); // Var3 = MM
        constraints.add(modelKB.arithm(vars[2], "=", 17)); // Var3 = MN
        constraints.add(modelKB.arithm(vars[2], "=", 18)); // Var3 = MS
        constraints.add(modelKB.arithm(vars[2], "=", 19)); // Var3 = MT
        constraints.add(modelKB.arithm(vars[2], "=", 20)); // Var3 = MU
        constraints.add(modelKB.arithm(vars[2], "=", 21)); // Var3 = MY
        constraints.add(modelKB.arithm(vars[2], "=", 22)); // Var3 = ND1G
        constraints.add(modelKB.arithm(vars[2], "=", 23)); // Var3 = NM0C
        constraints.add(modelKB.arithm(vars[2], "=", 24)); // Var3 = NM2K

        // Var4 : DIESEL, ESS
        constraints.add(modelKB.arithm(vars[3], "=", 0)); // Var4 = DIESEL
        constraints.add(modelKB.arithm(vars[3], "=", 1)); // Var4 = ESS

        // Var5 : AFSU, ALLE, ARGE, AUST, AUTR, BELG, BRES, CETI, CHIL, COLO, DAIB, DAIC, DAID, DAIF, DANE, DOTO, ESPA, EUOR, FINL, FRAN, GRBR, GREC, HOLL, HONG, IRLA, ISLA, ISRA, ITAL, JAPO, MAGH, MARO, NORV, POLO, PORT, SLVQ, SUED, SUIS, TAIW, TCHE, TURQ, URUG, YOUG
        constraints.add(modelKB.arithm(vars[4], "=", 0)); // Var5 = AFSU
        constraints.add(modelKB.arithm(vars[4], "=", 1)); // Var5 = ALLE
        constraints.add(modelKB.arithm(vars[4], "=", 2)); // Var5 = ARGE
        constraints.add(modelKB.arithm(vars[4], "=", 3)); // Var5 = AUST
        constraints.add(modelKB.arithm(vars[4], "=", 4)); // Var5 = AUTR
        constraints.add(modelKB.arithm(vars[4], "=", 5)); // Var5 = BELG
        constraints.add(modelKB.arithm(vars[4], "=", 6)); // Var5 = BRES
        constraints.add(modelKB.arithm(vars[4], "=", 7)); // Var5 = CETI
        constraints.add(modelKB.arithm(vars[4], "=", 8)); // Var5 = CHIL
        constraints.add(modelKB.arithm(vars[4], "=", 9)); // Var5 = COLO
        constraints.add(modelKB.arithm(vars[4], "=", 10)); // Var5 = DAIB
        constraints.add(modelKB.arithm(vars[4], "=", 11)); // Var5 = DAIC
        constraints.add(modelKB.arithm(vars[4], "=", 12)); // Var5 = DAID
        constraints.add(modelKB.arithm(vars[4], "=", 13)); // Var5 = DAIF
        constraints.add(modelKB.arithm(vars[4], "=", 14)); // Var5 = DANE
        constraints.add(modelKB.arithm(vars[4], "=", 15)); // Var5 = DOTO
        constraints.add(modelKB.arithm(vars[4], "=", 16)); // Var5 = ESPA
        constraints.add(modelKB.arithm(vars[4], "=", 17)); // Var5 = EUOR
        constraints.add(modelKB.arithm(vars[4], "=", 18)); // Var5 = FINL
        constraints.add(modelKB.arithm(vars[4], "=", 19)); // Var5 = FRAN
        constraints.add(modelKB.arithm(vars[4], "=", 20)); // Var5 = GRBR
        constraints.add(modelKB.arithm(vars[4], "=", 21)); // Var5 = GREC
        constraints.add(modelKB.arithm(vars[4], "=", 22)); // Var5 = HOLL
        constraints.add(modelKB.arithm(vars[4], "=", 23)); // Var5 = HONG
        constraints.add(modelKB.arithm(vars[4], "=", 24)); // Var5 = IRLA
        constraints.add(modelKB.arithm(vars[4], "=", 25)); // Var5 = ISLA
        constraints.add(modelKB.arithm(vars[4], "=", 26)); // Var5 = ISRA
        constraints.add(modelKB.arithm(vars[4], "=", 27)); // Var5 = ITAL
        constraints.add(modelKB.arithm(vars[4], "=", 28)); // Var5 = JAPO
        constraints.add(modelKB.arithm(vars[4], "=", 29)); // Var5 = MAGH
        constraints.add(modelKB.arithm(vars[4], "=", 30)); // Var5 = MARO
        constraints.add(modelKB.arithm(vars[4], "=", 31)); // Var5 = NORV
        constraints.add(modelKB.arithm(vars[4], "=", 32)); // Var5 = POLO
        constraints.add(modelKB.arithm(vars[4], "=", 33)); // Var5 = PORT
        constraints.add(modelKB.arithm(vars[4], "=", 34)); // Var5 = SLVQ
        constraints.add(modelKB.arithm(vars[4], "=", 35)); // Var5 = SUED
        constraints.add(modelKB.arithm(vars[4], "=", 36)); // Var5 = SUIS
        constraints.add(modelKB.arithm(vars[4], "=", 37)); // Var5 = TAIW
        constraints.add(modelKB.arithm(vars[4], "=", 38)); // Var5 = TCHE
        constraints.add(modelKB.arithm(vars[4], "=", 39)); // Var5 = TURQ
        constraints.add(modelKB.arithm(vars[4], "=", 40)); // Var5 = URUG
        constraints.add(modelKB.arithm(vars[4], "=", 41)); // Var5 = YOUG

        // Var6 : DD, DG
        constraints.add(modelKB.arithm(vars[5], "=", 0)); // Var6 = DD
        constraints.add(modelKB.arithm(vars[5], "=", 1)); // Var6 = DG

        // Var7 : GDFROI, TEMP
        constraints.add(modelKB.arithm(vars[6], "=", 0)); // Var7 = GDFROI
        constraints.add(modelKB.arithm(vars[6], "=", 1)); // Var7 = TEMP

        // Var8 : CPLG, CPLN
        constraints.add(modelKB.arithm(vars[7], "=", 0)); // Var8 = CPLG
        constraints.add(modelKB.arithm(vars[7], "=", 1)); // Var8 = CPLN

        // Var9 : DA, DM
        constraints.add(modelKB.arithm(vars[8], "=", 0)); // Var9 = DA
        constraints.add(modelKB.arithm(vars[8], "=", 1)); // Var9 = DM

        // Var10 : SANCOA
        constraints.add(modelKB.arithm(vars[9], "=", 0)); // Var10 = SANCOA

        // Var11 : ABS, SSABS
        constraints.add(modelKB.arithm(vars[10], "=", 0)); // Var11 = ABS
        constraints.add(modelKB.arithm(vars[10], "=", 1)); // Var11 = SSABS

        // Var12 : GALERI, SGALER
        constraints.add(modelKB.arithm(vars[11], "=", 0)); // Var12 = GALERI
        constraints.add(modelKB.arithm(vars[11], "=", 1)); // Var12 = SGALER

        // Var13 : CA, CHAUFO
        constraints.add(modelKB.arithm(vars[12], "=", 0)); // Var13 = CA
        constraints.add(modelKB.arithm(vars[12], "=", 1)); // Var13 = CHAUFO

        // Var14 : CAPVMA, CATOEL, CATOMA, TN, TO, TODEGO
        constraints.add(modelKB.arithm(vars[13], "=", 0)); // Var14 = CAPVMA
        constraints.add(modelKB.arithm(vars[13], "=", 1)); // Var14 = CATOEL
        constraints.add(modelKB.arithm(vars[13], "=", 2)); // Var14 = CATOMA
        constraints.add(modelKB.arithm(vars[13], "=", 3)); // Var14 = TN
        constraints.add(modelKB.arithm(vars[13], "=", 4)); // Var14 = TO
        constraints.add(modelKB.arithm(vars[13], "=", 5)); // Var14 = TODEGO

        // Var15 : PBCH, PBNCH
        constraints.add(modelKB.arithm(vars[14], "=", 0)); // Var15 = PBCH
        constraints.add(modelKB.arithm(vars[14], "=", 1)); // Var15 = PBNCH

        // Var16 : VC, VT
        constraints.add(modelKB.arithm(vars[15], "=", 0)); // Var16 = VC
        constraints.add(modelKB.arithm(vars[15], "=", 1)); // Var16 = VT

        // Var17 : SPRTEL
        constraints.add(modelKB.arithm(vars[16], "=", 0)); // Var17 = SPRTEL

        // Var18 : ELA, SSELA
        constraints.add(modelKB.arithm(vars[17], "=", 0)); // Var18 = ELA
        constraints.add(modelKB.arithm(vars[17], "=", 1)); // Var18 = SSELA

        // Var19 : CPE, SSCPE
        constraints.add(modelKB.arithm(vars[18], "=", 0)); // Var19 = CPE
        constraints.add(modelKB.arithm(vars[18], "=", 1)); // Var19 = SSCPE

        // Var20 : SSTIR, TIR
        constraints.add(modelKB.arithm(vars[19], "=", 0)); // Var20 = SSTIR
        constraints.add(modelKB.arithm(vars[19], "=", 1)); // Var20 = TIR

        // Var21 : RETROE, RETROR
        constraints.add(modelKB.arithm(vars[20], "=", 0)); // Var21 = RETROE
        constraints.add(modelKB.arithm(vars[20], "=", 1)); // Var21 = RETROR

        // Var22 : REGSIT, SSRSIT
        constraints.add(modelKB.arithm(vars[21], "=", 0)); // Var22 = REGSIT
        constraints.add(modelKB.arithm(vars[21], "=", 1)); // Var22 = SSRSIT

        // Var23 : PROJAB, SPROJA
        constraints.add(modelKB.arithm(vars[22], "=", 0)); // Var23 = PROJAB
        constraints.add(modelKB.arithm(vars[22], "=", 1)); // Var23 = SPROJA

        // Var24 : SSARCE
        constraints.add(modelKB.arithm(vars[23], "=", 0)); // Var24 = SSARCE

        // Var25 : CUSFIX, CUSPIV, SANCUS
        constraints.add(modelKB.arithm(vars[24], "=", 0)); // Var25 = CUSFIX
        constraints.add(modelKB.arithm(vars[24], "=", 1)); // Var25 = CUSPIV
        constraints.add(modelKB.arithm(vars[24], "=", 2)); // Var25 = SANCUS

        // Var26 : CLB, CLCGRI, SANCL
        constraints.add(modelKB.arithm(vars[25], "=", 0)); // Var26 = CLB
        constraints.add(modelKB.arithm(vars[25], "=", 1)); // Var26 = CLCGRI
        constraints.add(modelKB.arithm(vars[25], "=", 2)); // Var26 = SANCL

        // Var27 : ADAC, SADAC
        constraints.add(modelKB.arithm(vars[26], "=", 0)); // Var27 = ADAC
        constraints.add(modelKB.arithm(vars[26], "=", 1)); // Var27 = SADAC

        // Var28 : CCHBAG, SCCHBA
        constraints.add(modelKB.arithm(vars[27], "=", 0)); // Var28 = CCHBAG
        constraints.add(modelKB.arithm(vars[27], "=", 1)); // Var28 = SCCHBA

        // Var29 : CUI, DRA
        constraints.add(modelKB.arithm(vars[28], "=", 0)); // Var29 = CUI
        constraints.add(modelKB.arithm(vars[28], "=", 1)); // Var29 = DRA

        // Var30 : ATARPH, CRIT3ATRPH, SSATAR
        constraints.add(modelKB.arithm(vars[29], "=", 0)); // Var30 = ATARPH
        constraints.add(modelKB.arithm(vars[29], "=", 1)); // Var30 = CRIT3ATRPH
        constraints.add(modelKB.arithm(vars[29], "=", 2)); // Var30 = SSATAR

        // Var31 : BANAR, CRIT3SJAR, CRIT3SJARI, FBANAR, FBARAC, SBANAR
        constraints.add(modelKB.arithm(vars[30], "=", 0)); // Var31 = BANAR
        constraints.add(modelKB.arithm(vars[30], "=", 1)); // Var31 = CRIT3SJAR
        constraints.add(modelKB.arithm(vars[30], "=", 2)); // Var31 = CRIT3SJARI
        constraints.add(modelKB.arithm(vars[30], "=", 3)); // Var31 = FBANAR
        constraints.add(modelKB.arithm(vars[30], "=", 4)); // Var31 = FBARAC
        constraints.add(modelKB.arithm(vars[30], "=", 5)); // Var31 = SBANAR

        // Var32 : SGMANU, SGMEMO
        constraints.add(modelKB.arithm(vars[31], "=", 0)); // Var32 = SGMANU
        constraints.add(modelKB.arithm(vars[31], "=", 1)); // Var32 = SGMEMO

        // Var33 : KM, MILES
        constraints.add(modelKB.arithm(vars[32], "=", 0)); // Var33 = KM
        constraints.add(modelKB.arithm(vars[32], "=", 1)); // Var33 = MILES

        // Var34 : Autre167, CPK01, CPK02
        constraints.add(modelKB.arithm(vars[33], "=", 0)); // Var34 = Autre167
        constraints.add(modelKB.arithm(vars[33], "=", 1)); // Var34 = CPK01
        constraints.add(modelKB.arithm(vars[33], "=", 2)); // Var34 = CPK02

        // Var35 : RIDOAR, SRIDAR
        constraints.add(modelKB.arithm(vars[34], "=", 0)); // Var35 = RIDOAR
        constraints.add(modelKB.arithm(vars[34], "=", 1)); // Var35 = SRIDAR

        // Var36 : PTCAV
        constraints.add(modelKB.arithm(vars[35], "=", 0)); // Var36 = PTCAV

        // Var39 : CATADI, SCATAD
        constraints.add(modelKB.arithm(vars[36], "=", 0)); // Var39 = CATADI
        constraints.add(modelKB.arithm(vars[36], "=", 1)); // Var39 = SCATAD

        // Var40 : EMBNOR, EMBPIL, SEMBRY
        constraints.add(modelKB.arithm(vars[37], "=", 0)); // Var40 = EMBNOR
        constraints.add(modelKB.arithm(vars[37], "=", 1)); // Var40 = EMBPIL
        constraints.add(modelKB.arithm(vars[37], "=", 2)); // Var40 = SEMBRY

        // Var41 : PNERFL, PNESTD
        constraints.add(modelKB.arithm(vars[38], "=", 0)); // Var41 = PNERFL
        constraints.add(modelKB.arithm(vars[38], "=", 1)); // Var41 = PNESTD

        // Var42 : PHAN01, PHAN02, SSPHAN
        constraints.add(modelKB.arithm(vars[39], "=", 0)); // Var42 = PHAN01
        constraints.add(modelKB.arithm(vars[39], "=", 1)); // Var42 = PHAN02
        constraints.add(modelKB.arithm(vars[39], "=", 2)); // Var42 = SSPHAN

        // Var43 : ETPN01, ETPN02, SSETPN
        constraints.add(modelKB.arithm(vars[40], "=", 0)); // Var43 = ETPN01
        constraints.add(modelKB.arithm(vars[40], "=", 1)); // Var43 = ETPN02
        constraints.add(modelKB.arithm(vars[40], "=", 2)); // Var43 = SSETPN

        // Var44 : Autre272, EQGPL, PREGPL
        constraints.add(modelKB.arithm(vars[41], "=", 0)); // Var44 = Autre272
        constraints.add(modelKB.arithm(vars[41], "=", 1)); // Var44 = EQGPL
        constraints.add(modelKB.arithm(vars[41], "=", 2)); // Var44 = PREGPL

        // Var45 : SUSNOR, SUSREN
        constraints.add(modelKB.arithm(vars[42], "=", 0)); // Var45 = SUSNOR
        constraints.add(modelKB.arithm(vars[42], "=", 1)); // Var45 = SUSREN

        // Var46 : RENTC, RETC
        constraints.add(modelKB.arithm(vars[43], "=", 0)); // Var46 = RENTC
        constraints.add(modelKB.arithm(vars[43], "=", 1)); // Var46 = RETC

        // Var47 : LVAVEL, LVAVMA
        constraints.add(modelKB.arithm(vars[44], "=", 0)); // Var47 = LVAVEL
        constraints.add(modelKB.arithm(vars[44], "=", 1)); // Var47 = LVAVMA

        // Var48 : LVAREL, LVARMA, SSLVAR
        constraints.add(modelKB.arithm(vars[45], "=", 0)); // Var48 = LVAREL
        constraints.add(modelKB.arithm(vars[45], "=", 1)); // Var48 = LVARMA
        constraints.add(modelKB.arithm(vars[45], "=", 2)); // Var48 = SSLVAR

        // Var49 : Autre310, SSAMVA
        constraints.add(modelKB.arithm(vars[46], "=", 0)); // Var49 = Autre310
        constraints.add(modelKB.arithm(vars[46], "=", 1)); // Var49 = SSAMVA

        // Var50 : SASURV
        constraints.add(modelKB.arithm(vars[47], "=", 0)); // Var50 = SASURV

        // Var51 : SGACHA, SGSCHA
        constraints.add(modelKB.arithm(vars[48], "=", 0)); // Var51 = SGACHA
        constraints.add(modelKB.arithm(vars[48], "=", 1)); // Var51 = SGSCHA

        // Var52 : COFIXE, COLOMB, COREHA, CORHLO
        constraints.add(modelKB.arithm(vars[49], "=", 0)); // Var52 = COFIXE
        constraints.add(modelKB.arithm(vars[49], "=", 1)); // Var52 = COLOMB
        constraints.add(modelKB.arithm(vars[49], "=", 2)); // Var52 = COREHA
        constraints.add(modelKB.arithm(vars[49], "=", 3)); // Var52 = CORHLO

        // Var53 : AVPFIL, SSPFIL
        constraints.add(modelKB.arithm(vars[50], "=", 0)); // Var53 = AVPFIL
        constraints.add(modelKB.arithm(vars[50], "=", 1)); // Var53 = SSPFIL

        // Var54 : NAVIG, SNAVIG
        constraints.add(modelKB.arithm(vars[51], "=", 0)); // Var54 = NAVIG
        constraints.add(modelKB.arithm(vars[51], "=", 1)); // Var54 = SNAVIG

        // Var55 : CRIT2RHENF, RHENF, SRHENF
        constraints.add(modelKB.arithm(vars[52], "=", 0)); // Var55 = CRIT2RHENF
        constraints.add(modelKB.arithm(vars[52], "=", 1)); // Var55 = RHENF
        constraints.add(modelKB.arithm(vars[52], "=", 2)); // Var55 = SRHENF

        // Var56 : APL01, APL02
        constraints.add(modelKB.arithm(vars[53], "=", 0)); // Var56 = APL01
        constraints.add(modelKB.arithm(vars[53], "=", 1)); // Var56 = APL02

        // Var57 : AILAR, SAILAR
        constraints.add(modelKB.arithm(vars[54], "=", 0)); // Var57 = AILAR
        constraints.add(modelKB.arithm(vars[54], "=", 1)); // Var57 = SAILAR

        // Var58 : MGMAZE, MGMECO, MGMINI, MGMRNA, MGMRNE, MGMRTA, MGMRTE, MGMRXT, RL, RN, RT, RXE, SMONEQ
        constraints.add(modelKB.arithm(vars[55], "=", 0)); // Var58 = MGMAZE
        constraints.add(modelKB.arithm(vars[55], "=", 1)); // Var58 = MGMECO
        constraints.add(modelKB.arithm(vars[55], "=", 2)); // Var58 = MGMINI
        constraints.add(modelKB.arithm(vars[55], "=", 3)); // Var58 = MGMRNA
        constraints.add(modelKB.arithm(vars[55], "=", 4)); // Var58 = MGMRNE
        constraints.add(modelKB.arithm(vars[55], "=", 5)); // Var58 = MGMRTA
        constraints.add(modelKB.arithm(vars[55], "=", 6)); // Var58 = MGMRTE
        constraints.add(modelKB.arithm(vars[55], "=", 7)); // Var58 = MGMRXT
        constraints.add(modelKB.arithm(vars[55], "=", 8)); // Var58 = RL
        constraints.add(modelKB.arithm(vars[55], "=", 9)); // Var58 = RN
        constraints.add(modelKB.arithm(vars[55], "=", 10)); // Var58 = RT
        constraints.add(modelKB.arithm(vars[55], "=", 11)); // Var58 = RXE
        constraints.add(modelKB.arithm(vars[55], "=", 12)); // Var58 = SMONEQ

        // Var59 : RUNLI, SRUNLI
        constraints.add(modelKB.arithm(vars[56], "=", 0)); // Var59 = RUNLI
        constraints.add(modelKB.arithm(vars[56], "=", 1)); // Var59 = SRUNLI

        // Var60 : LAVPH, SLAVPH
        constraints.add(modelKB.arithm(vars[57], "=", 0)); // Var60 = LAVPH
        constraints.add(modelKB.arithm(vars[57], "=", 1)); // Var60 = SLAVPH

        // Var61 : EVCFIX, EVCVAR
        constraints.add(modelKB.arithm(vars[58], "=", 0)); // Var61 = EVCFIX
        constraints.add(modelKB.arithm(vars[58], "=", 1)); // Var61 = EVCVAR

        // Var62 : AOEF, SAOEF
        constraints.add(modelKB.arithm(vars[59], "=", 0)); // Var62 = AOEF
        constraints.add(modelKB.arithm(vars[59], "=", 1)); // Var62 = SAOEF

        // Var63 : VOLNRH, VOLRH
        constraints.add(modelKB.arithm(vars[60], "=", 0)); // Var63 = VOLNRH
        constraints.add(modelKB.arithm(vars[60], "=", 1)); // Var63 = VOLRH

        // Var64 : PANP00, PANP01, PANP02, PANP03, PANP05
        constraints.add(modelKB.arithm(vars[61], "=", 0)); // Var64 = PANP00
        constraints.add(modelKB.arithm(vars[61], "=", 1)); // Var64 = PANP01
        constraints.add(modelKB.arithm(vars[61], "=", 2)); // Var64 = PANP02
        constraints.add(modelKB.arithm(vars[61], "=", 3)); // Var64 = PANP03
        constraints.add(modelKB.arithm(vars[61], "=", 4)); // Var64 = PANP05

        // Var65 : PLAFNT, PLAFT
        constraints.add(modelKB.arithm(vars[62], "=", 0)); // Var65 = PLAFNT
        constraints.add(modelKB.arithm(vars[62], "=", 1)); // Var65 = PLAFT

        // Var66 : JANALU, JANTOL
        constraints.add(modelKB.arithm(vars[63], "=", 0)); // Var66 = JANALU
        constraints.add(modelKB.arithm(vars[63], "=", 1)); // Var66 = JANTOL

        // Var67 : T0, T1, T2, T3, T5
        constraints.add(modelKB.arithm(vars[64], "=", 0)); // Var67 = T0
        constraints.add(modelKB.arithm(vars[64], "=", 1)); // Var67 = T1
        constraints.add(modelKB.arithm(vars[64], "=", 2)); // Var67 = T2
        constraints.add(modelKB.arithm(vars[64], "=", 3)); // Var67 = T3
        constraints.add(modelKB.arithm(vars[64], "=", 4)); // Var67 = T5

        // Var68 : PSCOMI, PSCOMR, PSCOPO
        constraints.add(modelKB.arithm(vars[65], "=", 0)); // Var68 = PSCOMI
        constraints.add(modelKB.arithm(vars[65], "=", 1)); // Var68 = PSCOMR
        constraints.add(modelKB.arithm(vars[65], "=", 2)); // Var68 = PSCOPO

        // Var69 : PSPAMI, PSPAMR
        constraints.add(modelKB.arithm(vars[66], "=", 0)); // Var69 = PSPAMI
        constraints.add(modelKB.arithm(vars[66], "=", 1)); // Var69 = PSPAMR

        // Var70 : ETAP01, ETAP03, SSETAP
        constraints.add(modelKB.arithm(vars[67], "=", 0)); // Var70 = ETAP01
        constraints.add(modelKB.arithm(vars[67], "=", 1)); // Var70 = ETAP03
        constraints.add(modelKB.arithm(vars[67], "=", 2)); // Var70 = SSETAP

        // Var71 : FSTPO
        constraints.add(modelKB.arithm(vars[68], "=", 0)); // Var71 = FSTPO

        // Var72 : DUCA, FUJI, KANG, ODIN, PARALL, PARBRE, PARGBR, PARPOL, PARTCH, SSEDNC, VRMI
        constraints.add(modelKB.arithm(vars[69], "=", 0)); // Var72 = DUCA
        constraints.add(modelKB.arithm(vars[69], "=", 1)); // Var72 = FUJI
        constraints.add(modelKB.arithm(vars[69], "=", 2)); // Var72 = KANG
        constraints.add(modelKB.arithm(vars[69], "=", 3)); // Var72 = ODIN
        constraints.add(modelKB.arithm(vars[69], "=", 4)); // Var72 = PARALL
        constraints.add(modelKB.arithm(vars[69], "=", 5)); // Var72 = PARBRE
        constraints.add(modelKB.arithm(vars[69], "=", 6)); // Var72 = PARGBR
        constraints.add(modelKB.arithm(vars[69], "=", 7)); // Var72 = PARPOL
        constraints.add(modelKB.arithm(vars[69], "=", 8)); // Var72 = PARTCH
        constraints.add(modelKB.arithm(vars[69], "=", 9)); // Var72 = SSEDNC
        constraints.add(modelKB.arithm(vars[69], "=", 10)); // Var72 = VRMI

        // Var73 : TBOR00, TBOR01, TBOR02, TBOR03, TBOR05
        constraints.add(modelKB.arithm(vars[70], "=", 0)); // Var73 = TBOR00
        constraints.add(modelKB.arithm(vars[70], "=", 1)); // Var73 = TBOR01
        constraints.add(modelKB.arithm(vars[70], "=", 2)); // Var73 = TBOR02
        constraints.add(modelKB.arithm(vars[70], "=", 3)); // Var73 = TBOR03
        constraints.add(modelKB.arithm(vars[70], "=", 4)); // Var73 = TBOR05

        // Var74 : TBOR00, TBOR01, TBOR02, TBOR03, TBOR05
        constraints.add(modelKB.arithm(vars[71], "=", 0)); // Var74 = TBOR00
        constraints.add(modelKB.arithm(vars[71], "=", 1)); // Var74 = TBOR01
        constraints.add(modelKB.arithm(vars[71], "=", 2)); // Var74 = TBOR02
        constraints.add(modelKB.arithm(vars[71], "=", 3)); // Var74 = TBOR03
        constraints.add(modelKB.arithm(vars[71], "=", 4)); // Var74 = TBOR05

        // Var75 : MOCY01, MOCY02, MOCY03, MOCY04, MOCY05, MOCY06, MOCY07, MOCY08, MOCY10, MOCY11, MOCY12, SSMOCY
        constraints.add(modelKB.arithm(vars[72], "=", 0)); // Var75 = MOCY01
        constraints.add(modelKB.arithm(vars[72], "=", 1)); // Var75 = MOCY02
        constraints.add(modelKB.arithm(vars[72], "=", 2)); // Var75 = MOCY03
        constraints.add(modelKB.arithm(vars[72], "=", 3)); // Var75 = MOCY04
        constraints.add(modelKB.arithm(vars[72], "=", 4)); // Var75 = MOCY05
        constraints.add(modelKB.arithm(vars[72], "=", 5)); // Var75 = MOCY06
        constraints.add(modelKB.arithm(vars[72], "=", 6)); // Var75 = MOCY07
        constraints.add(modelKB.arithm(vars[72], "=", 7)); // Var75 = MOCY08
        constraints.add(modelKB.arithm(vars[72], "=", 8)); // Var75 = MOCY10
        constraints.add(modelKB.arithm(vars[72], "=", 9)); // Var75 = MOCY11
        constraints.add(modelKB.arithm(vars[72], "=", 10)); // Var75 = MOCY12
        constraints.add(modelKB.arithm(vars[72], "=", 11)); // Var75 = SSMOCY

        // Var76 : Autre408, NINAV1
        constraints.add(modelKB.arithm(vars[73], "=", 0)); // Var76 = Autre408
        constraints.add(modelKB.arithm(vars[73], "=", 1)); // Var76 = NINAV1

        // Var77 : ABPA01, SSABPA
        constraints.add(modelKB.arithm(vars[74], "=", 0)); // Var77 = ABPA01
        constraints.add(modelKB.arithm(vars[74], "=", 1)); // Var77 = SSABPA

        // Var78 : FIPOU, SFIPOU
        constraints.add(modelKB.arithm(vars[75], "=", 0)); // Var78 = FIPOU
        constraints.add(modelKB.arithm(vars[75], "=", 1)); // Var78 = SFIPOU

        // Var79 : ABCO01, SSABCO
        constraints.add(modelKB.arithm(vars[76], "=", 0)); // Var79 = ABCO01
        constraints.add(modelKB.arithm(vars[76], "=", 1)); // Var79 = SSABCO

        // Var80 : ANSRAD, CRIT2X5KS, CRIT2X8KI, CRIT4X15CI, CRIT4X15KI, CRIT4X25KI, SRADIO
        constraints.add(modelKB.arithm(vars[77], "=", 0)); // Var80 = ANSRAD
        constraints.add(modelKB.arithm(vars[77], "=", 1)); // Var80 = CRIT2X5KS
        constraints.add(modelKB.arithm(vars[77], "=", 2)); // Var80 = CRIT2X8KI
        constraints.add(modelKB.arithm(vars[77], "=", 3)); // Var80 = CRIT4X15CI
        constraints.add(modelKB.arithm(vars[77], "=", 4)); // Var80 = CRIT4X15KI
        constraints.add(modelKB.arithm(vars[77], "=", 5)); // Var80 = CRIT4X25KI
        constraints.add(modelKB.arithm(vars[77], "=", 6)); // Var80 = SRADIO

        // Var81 : BVA4, BVM5
        constraints.add(modelKB.arithm(vars[78], "=", 0)); // Var81 = BVA4
        constraints.add(modelKB.arithm(vars[78], "=", 1)); // Var81 = BVM5

        // Var82 : NMAS01, NMAS02, NMAS03, NMAS04, NMAS05
        constraints.add(modelKB.arithm(vars[79], "=", 0)); // Var82 = NMAS01
        constraints.add(modelKB.arithm(vars[79], "=", 1)); // Var82 = NMAS02
        constraints.add(modelKB.arithm(vars[79], "=", 2)); // Var82 = NMAS03
        constraints.add(modelKB.arithm(vars[79], "=", 3)); // Var82 = NMAS04
        constraints.add(modelKB.arithm(vars[79], "=", 4)); // Var82 = NMAS05

        // Var83 : Autre432, PLARPT, PLARVN, PLARVO
        constraints.add(modelKB.arithm(vars[80], "=", 0)); // Var83 = Autre432
        constraints.add(modelKB.arithm(vars[80], "=", 1)); // Var83 = PLARPT
        constraints.add(modelKB.arithm(vars[80], "=", 2)); // Var83 = PLARVN
        constraints.add(modelKB.arithm(vars[80], "=", 3)); // Var83 = PLARVO

        // Var84 : ECLAR, SECLAR
        constraints.add(modelKB.arithm(vars[81], "=", 0)); // Var84 = ECLAR
        constraints.add(modelKB.arithm(vars[81], "=", 1)); // Var84 = SECLAR

        // Var85 : CDCOF, SCDCOF
        constraints.add(modelKB.arithm(vars[82], "=", 0)); // Var85 = CDCOF
        constraints.add(modelKB.arithm(vars[82], "=", 1)); // Var85 = SCDCOF

        // Var86 : ACPLAR, SACPLA
        constraints.add(modelKB.arithm(vars[83], "=", 0)); // Var86 = ACPLAR
        constraints.add(modelKB.arithm(vars[83], "=", 1)); // Var86 = SACPLA

        // Var87 : MONORM, SURMO1
        constraints.add(modelKB.arithm(vars[84], "=", 0)); // Var87 = MONORM
        constraints.add(modelKB.arithm(vars[84], "=", 1)); // Var87 = SURMO1

        // Var88 : Autre497, JANDIF
        constraints.add(modelKB.arithm(vars[85], "=", 0)); // Var88 = Autre497
        constraints.add(modelKB.arithm(vars[85], "=", 1)); // Var88 = JANDIF

        // Var89 : Autre513, EVA, EVE
        constraints.add(modelKB.arithm(vars[86], "=", 0)); // Var89 = Autre513
        constraints.add(modelKB.arithm(vars[86], "=", 1)); // Var89 = EVA
        constraints.add(modelKB.arithm(vars[86], "=", 2)); // Var89 = EVE

        // Var90 : ANTID, ANTIDI, SDPCLV
        constraints.add(modelKB.arithm(vars[87], "=", 0)); // Var90 = ANTID
        constraints.add(modelKB.arithm(vars[87], "=", 1)); // Var90 = ANTIDI
        constraints.add(modelKB.arithm(vars[87], "=", 2)); // Var90 = SDPCLV

        // Var91 : Autre613, TKO
        constraints.add(modelKB.arithm(vars[88], "=", 0)); // Var91 = Autre613
        constraints.add(modelKB.arithm(vars[88], "=", 1)); // Var91 = TKO

        // Var92 : Autre713, CPNT01, CPNT02, CPNT03, EQDIF
        constraints.add(modelKB.arithm(vars[89], "=", 0)); // Var92 = Autre713
        constraints.add(modelKB.arithm(vars[89], "=", 1)); // Var92 = CPNT01
        constraints.add(modelKB.arithm(vars[89], "=", 2)); // Var92 = CPNT02
        constraints.add(modelKB.arithm(vars[89], "=", 3)); // Var92 = CPNT03
        constraints.add(modelKB.arithm(vars[89], "=", 4)); // Var92 = EQDIF

        // Var93 : Autre714, BVDIF
        constraints.add(modelKB.arithm(vars[90], "=", 0)); // Var93 = Autre714
        constraints.add(modelKB.arithm(vars[90], "=", 1)); // Var93 = BVDIF

        // Var94 : CRIT1503, EU00, EU93, EU96
        constraints.add(modelKB.arithm(vars[91], "=", 0)); // Var94 = CRIT1503
        constraints.add(modelKB.arithm(vars[91], "=", 1)); // Var94 = EU00
        constraints.add(modelKB.arithm(vars[91], "=", 2)); // Var94 = EU93
        constraints.add(modelKB.arithm(vars[91], "=", 3)); // Var94 = EU96

        // Var95 : CRIT1149CC, CRIT1390CC, CRIT1598CC, CRIT1870CC, CRIT1998CC
        constraints.add(modelKB.arithm(vars[92], "=", 0)); // Var95 = CRIT1149CC
        constraints.add(modelKB.arithm(vars[92], "=", 1)); // Var95 = CRIT1390CC
        constraints.add(modelKB.arithm(vars[92], "=", 2)); // Var95 = CRIT1598CC
        constraints.add(modelKB.arithm(vars[92], "=", 3)); // Var95 = CRIT1870CC
        constraints.add(modelKB.arithm(vars[92], "=", 4)); // Var95 = CRIT1998CC

        // Var96 : CRIT060CV, CRIT065CV, CRIT070CV, CRIT075CV, CRIT090CV, CRIT095CV, CRIT100CV, CRIT105CV, CRIT115CV, CRIT150CV
        constraints.add(modelKB.arithm(vars[93], "=", 0)); // Var96 = CRIT060CV
        constraints.add(modelKB.arithm(vars[93], "=", 1)); // Var96 = CRIT065CV
        constraints.add(modelKB.arithm(vars[93], "=", 2)); // Var96 = CRIT070CV
        constraints.add(modelKB.arithm(vars[93], "=", 3)); // Var96 = CRIT075CV
        constraints.add(modelKB.arithm(vars[93], "=", 4)); // Var96 = CRIT090CV
        constraints.add(modelKB.arithm(vars[93], "=", 5)); // Var96 = CRIT095CV
        constraints.add(modelKB.arithm(vars[93], "=", 6)); // Var96 = CRIT100CV
        constraints.add(modelKB.arithm(vars[93], "=", 7)); // Var96 = CRIT105CV
        constraints.add(modelKB.arithm(vars[93], "=", 8)); // Var96 = CRIT115CV
        constraints.add(modelKB.arithm(vars[93], "=", 9)); // Var96 = CRIT150CV

        // Var97 : BCNTC, BCTC
        constraints.add(modelKB.arithm(vars[94], "=", 0)); // Var97 = BCNTC
        constraints.add(modelKB.arithm(vars[94], "=", 1)); // Var97 = BCTC

        // Var98 : AZE, Autre913, CPTECO
        constraints.add(modelKB.arithm(vars[95], "=", 0)); // Var98 = AZE
        constraints.add(modelKB.arithm(vars[95], "=", 1)); // Var98 = Autre913
        constraints.add(modelKB.arithm(vars[95], "=", 2)); // Var98 = CPTECO

        // Var99 : AD4, DP0, JB1, JB3, JC5
        constraints.add(modelKB.arithm(vars[96], "=", 0)); // Var99 = AD4
        constraints.add(modelKB.arithm(vars[96], "=", 1)); // Var99 = DP0
        constraints.add(modelKB.arithm(vars[96], "=", 2)); // Var99 = JB1
        constraints.add(modelKB.arithm(vars[96], "=", 3)); // Var99 = JB3
        constraints.add(modelKB.arithm(vars[96], "=", 4)); // Var99 = JC5

        // Var100 : CRIT620, CRIT622, CRIT624, CRIT700, CRIT701, CRIT702, CRIT703, CRIT710, CRIT714, CRIT720, CRIT730, CRIT731, CRIT732, CRIT733, CRIT734, CRIT736, CRIT740, CRIT750, CRIT751, CRIT752, CRIT764, CRIT784, CRIT786, CRIT788, CRIT790, CRIT791, CRIT796, CRIT797, CRIT798
        constraints.add(modelKB.arithm(vars[97], "=", 0)); // Var100 = CRIT620
        constraints.add(modelKB.arithm(vars[97], "=", 1)); // Var100 = CRIT622
        constraints.add(modelKB.arithm(vars[97], "=", 2)); // Var100 = CRIT624
        constraints.add(modelKB.arithm(vars[97], "=", 3)); // Var100 = CRIT700
        constraints.add(modelKB.arithm(vars[97], "=", 4)); // Var100 = CRIT701
        constraints.add(modelKB.arithm(vars[97], "=", 5)); // Var100 = CRIT702
        constraints.add(modelKB.arithm(vars[97], "=", 6)); // Var100 = CRIT703
        constraints.add(modelKB.arithm(vars[97], "=", 7)); // Var100 = CRIT710
        constraints.add(modelKB.arithm(vars[97], "=", 8)); // Var100 = CRIT714
        constraints.add(modelKB.arithm(vars[97], "=", 9)); // Var100 = CRIT720
        constraints.add(modelKB.arithm(vars[97], "=", 10)); // Var100 = CRIT730
        constraints.add(modelKB.arithm(vars[97], "=", 11)); // Var100 = CRIT731
        constraints.add(modelKB.arithm(vars[97], "=", 12)); // Var100 = CRIT732
        constraints.add(modelKB.arithm(vars[97], "=", 13)); // Var100 = CRIT733
        constraints.add(modelKB.arithm(vars[97], "=", 14)); // Var100 = CRIT734
        constraints.add(modelKB.arithm(vars[97], "=", 15)); // Var100 = CRIT736
        constraints.add(modelKB.arithm(vars[97], "=", 16)); // Var100 = CRIT740
        constraints.add(modelKB.arithm(vars[97], "=", 17)); // Var100 = CRIT750
        constraints.add(modelKB.arithm(vars[97], "=", 18)); // Var100 = CRIT751
        constraints.add(modelKB.arithm(vars[97], "=", 19)); // Var100 = CRIT752
        constraints.add(modelKB.arithm(vars[97], "=", 20)); // Var100 = CRIT764
        constraints.add(modelKB.arithm(vars[97], "=", 21)); // Var100 = CRIT784
        constraints.add(modelKB.arithm(vars[97], "=", 22)); // Var100 = CRIT786
        constraints.add(modelKB.arithm(vars[97], "=", 23)); // Var100 = CRIT788
        constraints.add(modelKB.arithm(vars[97], "=", 24)); // Var100 = CRIT790
        constraints.add(modelKB.arithm(vars[97], "=", 25)); // Var100 = CRIT791
        constraints.add(modelKB.arithm(vars[97], "=", 26)); // Var100 = CRIT796
        constraints.add(modelKB.arithm(vars[97], "=", 27)); // Var100 = CRIT797
        constraints.add(modelKB.arithm(vars[97], "=", 28)); // Var100 = CRIT798

        // Var101 : D7F, E7J, F3R, F4R, F7R, F8Q, F9Q, K4J, K4M, K7M
        constraints.add(modelKB.arithm(vars[98], "=", 0)); // Var101 = D7F
        constraints.add(modelKB.arithm(vars[98], "=", 1)); // Var101 = E7J
        constraints.add(modelKB.arithm(vars[98], "=", 2)); // Var101 = F3R
        constraints.add(modelKB.arithm(vars[98], "=", 3)); // Var101 = F4R
        constraints.add(modelKB.arithm(vars[98], "=", 4)); // Var101 = F7R
        constraints.add(modelKB.arithm(vars[98], "=", 5)); // Var101 = F8Q
        constraints.add(modelKB.arithm(vars[98], "=", 6)); // Var101 = F9Q
        constraints.add(modelKB.arithm(vars[98], "=", 7)); // Var101 = K4J
        constraints.add(modelKB.arithm(vars[98], "=", 8)); // Var101 = K4M
        constraints.add(modelKB.arithm(vars[98], "=", 9)); // Var101 = K7M

        return constraints;
    }

    @Override
    public Model getModelKB() {
        return modelKB;
    }

    @Override
    public void setModelKB(Model m) {
        modelKB = m;
    }

    @Override
    public int getNumberOfVariables() {
        return numberOfVariables;
    }

    @Override
    public void setNumberOfVariables(int n) {
        numberOfVariables = n;
    }

    @Override
    public IntVar[] getVars() {
        return vars;
    }

    @Override
    public void setVars(IntVar[] v) {
        vars=v;
    }

    public static List<Constraint> arrayToList(Constraint[] cstrs)
    {
        List<Constraint> l = new ArrayList<Constraint>();
        for (int i=0; i<cstrs.length; i++)
            l.add(cstrs[i]);
        return l;
    }

    @Override
    public int[][] getDomains() {
        return domains;
    }

    public int[] getDomainSizes() {
        return domainSizes;
    }

    public int getIndexVariable(String var) {
        switch (var) {
            case "Var1": return 0;
            case "Var2": return 1;
            case "Var3": return 2;
            case "Var4": return 3;
            case "Var5": return 4;
            case "Var6": return 5;
            case "Var7": return 6;
            case "Var8": return 7;
            case "Var9": return 8;
            case "Var10": return 9;
            case "Var11": return 10;
            case "Var12": return 11;
            case "Var13": return 12;
            case "Var14": return 13;
            case "Var15": return 14;
            case "Var16": return 15;
            case "Var17": return 16;
            case "Var18": return 17;
            case "Var19": return 18;
            case "Var20": return 19;
            case "Var21": return 20;
            case "Var22": return 21;
            case "Var23": return 22;
            case "Var24": return 23;
            case "Var25": return 24;
            case "Var26": return 25;
            case "Var27": return 26;
            case "Var28": return 27;
            case "Var29": return 28;
            case "Var30": return 29;
            case "Var31": return 30;
            case "Var32": return 31;
            case "Var33": return 32;
            case "Var34": return 33;
            case "Var35": return 34;
            case "Var36": return 35;
            case "Var39": return 36;
            case "Var40": return 37;
            case "Var41": return 38;
            case "Var42": return 39;
            case "Var43": return 40;
            case "Var44": return 41;
            case "Var45": return 42;
            case "Var46": return 43;
            case "Var47": return 44;
            case "Var48": return 45;
            case "Var49": return 46;
            case "Var50": return 47;
            case "Var51": return 48;
            case "Var52": return 49;
            case "Var53": return 50;
            case "Var54": return 51;
            case "Var55": return 52;
            case "Var56": return 53;
            case "Var57": return 54;
            case "Var58": return 55;
            case "Var59": return 56;
            case "Var60": return 57;
            case "Var61": return 58;
            case "Var62": return 59;
            case "Var63": return 60;
            case "Var64": return 61;
            case "Var65": return 62;
            case "Var66": return 63;
            case "Var67": return 64;
            case "Var68": return 65;
            case "Var69": return 66;
            case "Var70": return 67;
            case "Var71": return 68;
            case "Var72": return 69;
            case "Var73": return 70;
            case "Var74": return 71;
            case "Var75": return 72;
            case "Var76": return 73;
            case "Var77": return 74;
            case "Var78": return 75;
            case "Var79": return 76;
            case "Var80": return 77;
            case "Var81": return 78;
            case "Var82": return 79;
            case "Var83": return 80;
            case "Var84": return 81;
            case "Var85": return 82;
            case "Var86": return 83;
            case "Var87": return 84;
            case "Var88": return 85;
            case "Var89": return 86;
            case "Var90": return 87;
            case "Var91": return 88;
            case "Var92": return 89;
            case "Var93": return 90;
            case "Var94": return 91;
            case "Var95": return 92;
            case "Var96": return 93;
            case "Var97": return 94;
            case "Var98": return 95;
            case "Var99": return 96;
            case "Var100": return 97;
            case "Var101": return 98;
            default: return -1;
        }
    }

    public int getIndexValue(String var, String value) {
        switch (var) {
            case "Var1":
                switch (value) {
                    case "B64": return 0;
                    case "D64": return 1;
                    case "E64": return 2;
                    case "F64": return 3;
                    case "J64": return 4;
                    case "K25": return 5;
                    case "L64": return 6;
                    case "S64": return 7;
                    case "V25": return 8;
                    default: return -1;
                }
            case "Var2":
                switch (value) {
                    case "E0": return 0;
                    case "E1": return 1;
                    case "E2": return 2;
                    case "E3": return 3;
                    case "E5": return 4;
                    default: return -1;
                }
            case "Var3":
                switch (value) {
                    case "M5": return 0;
                    case "M6": return 1;
                    case "M7": return 2;
                    case "M8": return 3;
                    case "M9": return 4;
                    case "MA": return 5;
                    case "MB": return 6;
                    case "MC": return 7;
                    case "MD": return 8;
                    case "ME": return 9;
                    case "MF": return 10;
                    case "MG": return 11;
                    case "MH": return 12;
                    case "MJ": return 13;
                    case "MK": return 14;
                    case "ML": return 15;
                    case "MM": return 16;
                    case "MN": return 17;
                    case "MS": return 18;
                    case "MT": return 19;
                    case "MU": return 20;
                    case "MY": return 21;
                    case "ND1G": return 22;
                    case "NM0C": return 23;
                    case "NM2K": return 24;
                    default: return -1;
                }
            case "Var4":
                switch (value) {
                    case "DIESEL": return 0;
                    case "ESS": return 1;
                    default: return -1;
                }
            case "Var5":
                switch (value) {
                    case "AFSU": return 0;
                    case "ALLE": return 1;
                    case "ARGE": return 2;
                    case "AUST": return 3;
                    case "AUTR": return 4;
                    case "BELG": return 5;
                    case "BRES": return 6;
                    case "CETI": return 7;
                    case "CHIL": return 8;
                    case "COLO": return 9;
                    case "DAIB": return 10;
                    case "DAIC": return 11;
                    case "DAID": return 12;
                    case "DAIF": return 13;
                    case "DANE": return 14;
                    case "DOTO": return 15;
                    case "ESPA": return 16;
                    case "EUOR": return 17;
                    case "FINL": return 18;
                    case "FRAN": return 19;
                    case "GRBR": return 20;
                    case "GREC": return 21;
                    case "HOLL": return 22;
                    case "HONG": return 23;
                    case "IRLA": return 24;
                    case "ISLA": return 25;
                    case "ISRA": return 26;
                    case "ITAL": return 27;
                    case "JAPO": return 28;
                    case "MAGH": return 29;
                    case "MARO": return 30;
                    case "NORV": return 31;
                    case "POLO": return 32;
                    case "PORT": return 33;
                    case "SLVQ": return 34;
                    case "SUED": return 35;
                    case "SUIS": return 36;
                    case "TAIW": return 37;
                    case "TCHE": return 38;
                    case "TURQ": return 39;
                    case "URUG": return 40;
                    case "YOUG": return 41;
                    default: return -1;
                }
            case "Var6":
                switch (value) {
                    case "DD": return 0;
                    case "DG": return 1;
                    default: return -1;
                }
            case "Var7":
                switch (value) {
                    case "GDFROI": return 0;
                    case "TEMP": return 1;
                    default: return -1;
                }
            case "Var8":
                switch (value) {
                    case "CPLG": return 0;
                    case "CPLN": return 1;
                    default: return -1;
                }
            case "Var9":
                switch (value) {
                    case "DA": return 0;
                    case "DM": return 1;
                    default: return -1;
                }
            case "Var10":
                switch (value) {
                    case "SANCOA": return 0;
                    default: return -1;
                }
            case "Var11":
                switch (value) {
                    case "ABS": return 0;
                    case "SSABS": return 1;
                    default: return -1;
                }
            case "Var12":
                switch (value) {
                    case "GALERI": return 0;
                    case "SGALER": return 1;
                    default: return -1;
                }
            case "Var13":
                switch (value) {
                    case "CA": return 0;
                    case "CHAUFO": return 1;
                    default: return -1;
                }
            case "Var14":
                switch (value) {
                    case "CAPVMA": return 0;
                    case "CATOEL": return 1;
                    case "CATOMA": return 2;
                    case "TN": return 3;
                    case "TO": return 4;
                    case "TODEGO": return 5;
                    default: return -1;
                }
            case "Var15":
                switch (value) {
                    case "PBCH": return 0;
                    case "PBNCH": return 1;
                    default: return -1;
                }
            case "Var16":
                switch (value) {
                    case "VC": return 0;
                    case "VT": return 1;
                    default: return -1;
                }
            case "Var17":
                switch (value) {
                    case "SPRTEL": return 0;
                    default: return -1;
                }
            case "Var18":
                switch (value) {
                    case "ELA": return 0;
                    case "SSELA": return 1;
                    default: return -1;
                }
            case "Var19":
                switch (value) {
                    case "CPE": return 0;
                    case "SSCPE": return 1;
                    default: return -1;
                }
            case "Var20":
                switch (value) {
                    case "SSTIR": return 0;
                    case "TIR": return 1;
                    default: return -1;
                }
            case "Var21":
                switch (value) {
                    case "RETROE": return 0;
                    case "RETROR": return 1;
                    default: return -1;
                }
            case "Var22":
                switch (value) {
                    case "REGSIT": return 0;
                    case "SSRSIT": return 1;
                    default: return -1;
                }
            case "Var23":
                switch (value) {
                    case "PROJAB": return 0;
                    case "SPROJA": return 1;
                    default: return -1;
                }
            case "Var24":
                switch (value) {
                    case "SSARCE": return 0;
                    default: return -1;
                }
            case "Var25":
                switch (value) {
                    case "CUSFIX": return 0;
                    case "CUSPIV": return 1;
                    case "SANCUS": return 2;
                    default: return -1;
                }
            case "Var26":
                switch (value) {
                    case "CLB": return 0;
                    case "CLCGRI": return 1;
                    case "SANCL": return 2;
                    default: return -1;
                }
            case "Var27":
                switch (value) {
                    case "ADAC": return 0;
                    case "SADAC": return 1;
                    default: return -1;
                }
            case "Var28":
                switch (value) {
                    case "CCHBAG": return 0;
                    case "SCCHBA": return 1;
                    default: return -1;
                }
            case "Var29":
                switch (value) {
                    case "CUI": return 0;
                    case "DRA": return 1;
                    default: return -1;
                }
            case "Var30":
                switch (value) {
                    case "ATARPH": return 0;
                    case "CRIT3ATRPH": return 1;
                    case "SSATAR": return 2;
                    default: return -1;
                }
            case "Var31":
                switch (value) {
                    case "BANAR": return 0;
                    case "CRIT3SJAR": return 1;
                    case "CRIT3SJARI": return 2;
                    case "FBANAR": return 3;
                    case "FBARAC": return 4;
                    case "SBANAR": return 5;
                    default: return -1;
                }
            case "Var32":
                switch (value) {
                    case "SGMANU": return 0;
                    case "SGMEMO": return 1;
                    default: return -1;
                }
            case "Var33":
                switch (value) {
                    case "KM": return 0;
                    case "MILES": return 1;
                    default: return -1;
                }
            case "Var34":
                switch (value) {
                    case "Autre167": return 0;
                    case "CPK01": return 1;
                    case "CPK02": return 2;
                    default: return -1;
                }
            case "Var35":
                switch (value) {
                    case "RIDOAR": return 0;
                    case "SRIDAR": return 1;
                    default: return -1;
                }
            case "Var36":
                switch (value) {
                    case "PTCAV": return 0;
                    default: return -1;
                }
            case "Var39":
                switch (value) {
                    case "CATADI": return 0;
                    case "SCATAD": return 1;
                    default: return -1;
                }
            case "Var40":
                switch (value) {
                    case "EMBNOR": return 0;
                    case "EMBPIL": return 1;
                    case "SEMBRY": return 2;
                    default: return -1;
                }
            case "Var41":
                switch (value) {
                    case "PNERFL": return 0;
                    case "PNESTD": return 1;
                    default: return -1;
                }
            case "Var42":
                switch (value) {
                    case "PHAN01": return 0;
                    case "PHAN02": return 1;
                    case "SSPHAN": return 2;
                    default: return -1;
                }
            case "Var43":
                switch (value) {
                    case "ETPN01": return 0;
                    case "ETPN02": return 1;
                    case "SSETPN": return 2;
                    default: return -1;
                }
            case "Var44":
                switch (value) {
                    case "Autre272": return 0;
                    case "EQGPL": return 1;
                    case "PREGPL": return 2;
                    default: return -1;
                }
            case "Var45":
                switch (value) {
                    case "SUSNOR": return 0;
                    case "SUSREN": return 1;
                    default: return -1;
                }
            case "Var46":
                switch (value) {
                    case "RENTC": return 0;
                    case "RETC": return 1;
                    default: return -1;
                }
            case "Var47":
                switch (value) {
                    case "LVAVEL": return 0;
                    case "LVAVMA": return 1;
                    default: return -1;
                }
            case "Var48":
                switch (value) {
                    case "LVAREL": return 0;
                    case "LVARMA": return 1;
                    case "SSLVAR": return 2;
                    default: return -1;
                }
            case "Var49":
                switch (value) {
                    case "Autre310": return 0;
                    case "SSAMVA": return 1;
                    default: return -1;
                }
            case "Var50":
                switch (value) {
                    case "SASURV": return 0;
                    default: return -1;
                }
            case "Var51":
                switch (value) {
                    case "SGACHA": return 0;
                    case "SGSCHA": return 1;
                    default: return -1;
                }
            case "Var52":
                switch (value) {
                    case "COFIXE": return 0;
                    case "COLOMB": return 1;
                    case "COREHA": return 2;
                    case "CORHLO": return 3;
                    default: return -1;
                }
            case "Var53":
                switch (value) {
                    case "AVPFIL": return 0;
                    case "SSPFIL": return 1;
                    default: return -1;
                }
            case "Var54":
                switch (value) {
                    case "NAVIG": return 0;
                    case "SNAVIG": return 1;
                    default: return -1;
                }
            case "Var55":
                switch (value) {
                    case "CRIT2RHENF": return 0;
                    case "RHENF": return 1;
                    case "SRHENF": return 2;
                    default: return -1;
                }
            case "Var56":
                switch (value) {
                    case "APL01": return 0;
                    case "APL02": return 1;
                    default: return -1;
                }
            case "Var57":
                switch (value) {
                    case "AILAR": return 0;
                    case "SAILAR": return 1;
                    default: return -1;
                }
            case "Var58":
                switch (value) {
                    case "MGMAZE": return 0;
                    case "MGMECO": return 1;
                    case "MGMINI": return 2;
                    case "MGMRNA": return 3;
                    case "MGMRNE": return 4;
                    case "MGMRTA": return 5;
                    case "MGMRTE": return 6;
                    case "MGMRXT": return 7;
                    case "RL": return 8;
                    case "RN": return 9;
                    case "RT": return 10;
                    case "RXE": return 11;
                    case "SMONEQ": return 12;
                    default: return -1;
                }
            case "Var59":
                switch (value) {
                    case "RUNLI": return 0;
                    case "SRUNLI": return 1;
                    default: return -1;
                }
            case "Var60":
                switch (value) {
                    case "LAVPH": return 0;
                    case "SLAVPH": return 1;
                    default: return -1;
                }
            case "Var61":
                switch (value) {
                    case "EVCFIX": return 0;
                    case "EVCVAR": return 1;
                    default: return -1;
                }
            case "Var62":
                switch (value) {
                    case "AOEF": return 0;
                    case "SAOEF": return 1;
                    default: return -1;
                }
            case "Var63":
                switch (value) {
                    case "VOLNRH": return 0;
                    case "VOLRH": return 1;
                    default: return -1;
                }
            case "Var64":
                switch (value) {
                    case "PANP00": return 0;
                    case "PANP01": return 1;
                    case "PANP02": return 2;
                    case "PANP03": return 3;
                    case "PANP05": return 4;
                    default: return -1;
                }
            case "Var65":
                switch (value) {
                    case "PLAFNT": return 0;
                    case "PLAFT": return 1;
                    default: return -1;
                }
            case "Var66":
                switch (value) {
                    case "JANALU": return 0;
                    case "JANTOL": return 1;
                    default: return -1;
                }
            case "Var67":
                switch (value) {
                    case "T0": return 0;
                    case "T1": return 1;
                    case "T2": return 2;
                    case "T3": return 3;
                    case "T5": return 4;
                    default: return -1;
                }
            case "Var68":
                switch (value) {
                    case "PSCOMI": return 0;
                    case "PSCOMR": return 1;
                    case "PSCOPO": return 2;
                    default: return -1;
                }
            case "Var69":
                switch (value) {
                    case "PSPAMI": return 0;
                    case "PSPAMR": return 1;
                    default: return -1;
                }
            case "Var70":
                switch (value) {
                    case "ETAP01": return 0;
                    case "ETAP03": return 1;
                    case "SSETAP": return 2;
                    default: return -1;
                }
            case "Var71":
                switch (value) {
                    case "FSTPO": return 0;
                    default: return -1;
                }
            case "Var72":
                switch (value) {
                    case "DUCA": return 0;
                    case "FUJI": return 1;
                    case "KANG": return 2;
                    case "ODIN": return 3;
                    case "PARALL": return 4;
                    case "PARBRE": return 5;
                    case "PARGBR": return 6;
                    case "PARPOL": return 7;
                    case "PARTCH": return 8;
                    case "SSEDNC": return 9;
                    case "VRMI": return 10;
                    default: return -1;
                }
            case "Var73":
                switch (value) {
                    case "TBOR00": return 0;
                    case "TBOR01": return 1;
                    case "TBOR02": return 2;
                    case "TBOR03": return 3;
                    case "TBOR05": return 4;
                    default: return -1;
                }
            case "Var74":
                switch (value) {
                    case "PBOR00": return 0;
                    case "PBOR01": return 1;
                    case "PBOR02": return 2;
                    case "PBOR03": return 3;
                    case "PBOR05": return 4;
                    default: return -1;
                }
            case "Var75":
                switch (value) {
                    case "MOCY01": return 0;
                    case "MOCY02": return 1;
                    case "MOCY03": return 2;
                    case "MOCY04": return 3;
                    case "MOCY05": return 4;
                    case "MOCY06": return 5;
                    case "MOCY07": return 6;
                    case "MOCY08": return 7;
                    case "MOCY10": return 8;
                    case "MOCY11": return 9;
                    case "MOCY12": return 10;
                    case "SSMOCY": return 11;
                    default: return -1;
                }
            case "Var76":
                switch (value) {
                    case "Autre408": return 0;
                    case "NINAV1": return 1;
                    default: return -1;
                }
            case "Var77":
                switch (value) {
                    case "ABPA01": return 0;
                    case "SSABPA": return 1;
                    default: return -1;
                }
            case "Var78":
                switch (value) {
                    case "FIPOU": return 0;
                    case "SFIPOU": return 1;
                    default: return -1;
                }
            case "Var79":
                switch (value) {
                    case "ABCO01": return 0;
                    case "SSABCO": return 1;
                    default: return -1;
                }
            case "Var80":
                switch (value) {
                    case "ANSRAD": return 0;
                    case "CRIT2X5KS": return 1;
                    case "CRIT2X8KI": return 2;
                    case "CRIT4X15CI": return 3;
                    case "CRIT4X15KI": return 4;
                    case "CRIT4X25KI": return 5;
                    case "SRADIO": return 6;
                    default: return -1;
                }
            case "Var81":
                switch (value) {
                    case "BVA4": return 0;
                    case "BVM5": return 1;
                    default: return -1;
                }
            case "Var82":
                switch (value) {
                    case "NMAS01": return 0;
                    case "NMAS02": return 1;
                    case "NMAS03": return 2;
                    case "NMAS04": return 3;
                    case "NMAS05": return 4;
                    default: return -1;
                }
            case "Var83":
                switch (value) {
                    case "Autre432": return 0;
                    case "PLARPT": return 1;
                    case "PLARVN": return 2;
                    case "PLARVO": return 3;
                    default: return -1;
                }
            case "Var84":
                switch (value) {
                    case "ECLAR": return 0;
                    case "SECLAR": return 1;
                    default: return -1;
                }
            case "Var85":
                switch (value) {
                    case "CDCOF": return 0;
                    case "SCDCOF": return 1;
                    default: return -1;
                }
            case "Var86":
                switch (value) {
                    case "ACPLAR": return 0;
                    case "SACPLA": return 1;
                    default: return -1;
                }
            case "Var87":
                switch (value) {
                    case "MONORM": return 0;
                    case "SURMO1": return 1;
                    default: return -1;
                }
            case "Var88":
                switch (value) {
                    case "Autre497": return 0;
                    case "JANDIF": return 1;
                    default: return -1;
                }
            case "Var89":
                switch (value) {
                    case "Autre513": return 0;
                    case "EVA": return 1;
                    case "EVE": return 2;
                    default: return -1;
                }
            case "Var90":
                switch (value) {
                    case "ANTID": return 0;
                    case "ANTIDI": return 1;
                    case "SDPCLV": return 2;
                    default: return -1;
                }
            case "Var91":
                switch (value) {
                    case "Autre613": return 0;
                    case "TKO": return 1;
                    default: return -1;
                }
            case "Var92":
                switch (value) {
                    case "Autre713": return 0;
                    case "CPNT01": return 1;
                    case "CPNT02": return 2;
                    case "CPNT03": return 3;
                    case "EQDIF": return 4;
                    default: return -1;
                }
            case "Var93":
                switch (value) {
                    case "Autre714": return 0;
                    case "BVDIF": return 1;
                    default: return -1;
                }
            case "Var94":
                switch (value) {
                    case "CRIT1503": return 0;
                    case "EU00": return 1;
                    case "EU93": return 2;
                    case "EU96": return 3;
                    default: return -1;
                }
            case "Var95":
                switch (value) {
                    case "CRIT1149CC": return 0;
                    case "CRIT1390CC": return 1;
                    case "CRIT1598CC": return 2;
                    case "CRIT1870CC": return 3;
                    case "CRIT1998CC": return 4;
                    default: return -1;
                }
            case "Var96":
                switch (value) {
                    case "CRIT060CV": return 0;
                    case "CRIT065CV": return 1;
                    case "CRIT070CV": return 2;
                    case "CRIT075CV": return 3;
                    case "CRIT090CV": return 4;
                    case "CRIT095CV": return 5;
                    case "CRIT100CV": return 6;
                    case "CRIT105CV": return 7;
                    case "CRIT115CV": return 8;
                    case "CRIT150CV": return 9;
                    default: return -1;
                }
            case "Var97":
                switch (value) {
                    case "BCNTC": return 0;
                    case "BCTC": return 1;
                    default: return -1;
                }
            case "Var98":
                switch (value) {
                    case "AZE": return 0;
                    case "Autre913": return 1;
                    case "CPTECO": return 2;
                    default: return -1;
                }
            case "Var99":
                switch (value) {
                    case "AD4": return 0;
                    case "DP0": return 1;
                    case "JB1": return 2;
                    case "JB3": return 3;
                    case "JC5": return 4;
                    default: return -1;
                }
            case "Var100":
                switch (value) {
                    case "CRIT620": return 0;
                    case "CRIT622": return 1;
                    case "CRIT624": return 2;
                    case "CRIT700": return 3;
                    case "CRIT701": return 4;
                    case "CRIT702": return 5;
                    case "CRIT703": return 6;
                    case "CRIT710": return 7;
                    case "CRIT714": return 8;
                    case "CRIT720": return 9;
                    case "CRIT730": return 10;
                    case "CRIT731": return 11;
                    case "CRIT732": return 12;
                    case "CRIT733": return 13;
                    case "CRIT734": return 14;
                    case "CRIT736": return 15;
                    case "CRIT740": return 16;
                    case "CRIT750": return 17;
                    case "CRIT751": return 18;
                    case "CRIT752": return 19;
                    case "CRIT764": return 20;
                    case "CRIT784": return 21;
                    case "CRIT786": return 22;
                    case "CRIT788": return 23;
                    case "CRIT790": return 24;
                    case "CRIT791": return 25;
                    case "CRIT796": return 26;
                    case "CRIT797": return 27;
                    case "CRIT798": return 28;
                    default: return -1;
                }
            case "Var101":
                switch (value) {
                    case "D7F": return 0;
                    case "E7J": return 1;
                    case "F3R": return 2;
                    case "F4R": return 3;
                    case "F7R": return 4;
                    case "F8Q": return 5;
                    case "F9Q": return 6;
                    case "K4J": return 7;
                    case "K4M": return 8;
                    case "K7M": return 9;
                    default: return -1;
                }
            default: return -1;
        }
    }
}