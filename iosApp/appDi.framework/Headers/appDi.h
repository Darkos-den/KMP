#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class AppDiKodein_diDIModule, AppDiCoreMVUState, AppDiKodein_typeTypeToken<T>, AppDiKotlinArray<T>, AppDiKodein_diDIContext<C>, AppDiKodein_diDIKey<__contravariant C, __contravariant A, __covariant T>, AppDiKodein_diScopeRegistry, AppDiKotlinUnit, AppDiKodein_diDITrigger, AppDiKodein_diReferenceLocal<__covariant T, __covariant R>, AppDiKodein_diDIDefinition<C, A, T>, AppDiKotlinTriple<__covariant A, __covariant B, __covariant C>, AppDiKodein_diSearchSpecs, AppDiKodein_diDIDefining<C, A, T>;

@protocol AppDiApiITimerComponent, AppDiKodein_diDIBuilder, AppDiProgramProgramComponent, AppDiKodein_diDIBuilderDirectBinder, AppDiKodein_diDIBuilderTypeBinder, AppDiKodein_diContextTranslator, AppDiKodein_diDIBuilderConstantBinder, AppDiKodein_diDirectDI, AppDiKodein_diDIContainerBuilder, AppDiKodein_diDIBindBuilder, AppDiKodein_diDIBindBuilderWithContext, AppDiKodein_diScope, AppDiKodein_diDIBindBuilderWithScope, AppDiKodein_diDIBinding, AppDiKotlinIterator, AppDiKodein_diDIContainer, AppDiKodein_diDI, AppDiKodein_diDirectDIAware, AppDiKodein_diDirectDIBase, AppDiKodein_diDIBindingCopier, AppDiKodein_diBindingDI, AppDiKodein_diBinding, AppDiKodein_diReference, AppDiKodein_diDITree, AppDiKodein_diDIAware, AppDiKodein_diScopeCloseable, AppDiKodein_diWithContext, AppDiKodein_diReferenceMaker, AppDiKodein_diExternalSource, AppDiKotlinLazy;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

__attribute__((swift_name("KotlinBase")))
@interface AppDiBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface AppDiBase (AppDiBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface AppDiMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface AppDiMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorAppDiKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface AppDiNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((swift_name("KotlinByte")))
@interface AppDiByte : AppDiNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface AppDiUByte : AppDiNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface AppDiShort : AppDiNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface AppDiUShort : AppDiNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface AppDiInt : AppDiNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface AppDiUInt : AppDiNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface AppDiLong : AppDiNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface AppDiULong : AppDiNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface AppDiFloat : AppDiNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface AppDiDouble : AppDiNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface AppDiBoolean : AppDiNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CommonInjector")))
@interface AppDiCommonInjector : AppDiBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)check __attribute__((swift_name("check()")));
- (AppDiKodein_diDIModule *)createAppModule __attribute__((swift_name("createAppModule()")));
- (id<AppDiApiITimerComponent>)getComponent __attribute__((swift_name("getComponent()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kodein_diDIModule")))
@interface AppDiKodein_diDIModule : AppDiBase
- (instancetype)initWithAllowSilentOverride:(BOOL)allowSilentOverride init:(void (^)(id<AppDiKodein_diDIBuilder>))init __attribute__((swift_name("init(allowSilentOverride:init:)"))) __attribute__((objc_designated_initializer)) __attribute__((deprecated("You should name your modules, for debug purposes.")));
- (instancetype)initWithName:(NSString *)name allowSilentOverride:(BOOL)allowSilentOverride prefix:(NSString *)prefix init:(void (^)(id<AppDiKodein_diDIBuilder>))init __attribute__((swift_name("init(name:allowSilentOverride:prefix:init:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (BOOL)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (void (^)(id<AppDiKodein_diDIBuilder>))component4 __attribute__((swift_name("component4()")));
- (AppDiKodein_diDIModule *)doCopyName:(NSString *)name allowSilentOverride:(BOOL)allowSilentOverride prefix:(NSString *)prefix init:(void (^)(id<AppDiKodein_diDIBuilder>))init __attribute__((swift_name("doCopy(name:allowSilentOverride:prefix:init:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL allowSilentOverride __attribute__((swift_name("allowSilentOverride")));
@property (readonly, getter=doInit) void (^init)(id<AppDiKodein_diDIBuilder>) __attribute__((swift_name("init")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *prefix __attribute__((swift_name("prefix")));
@end;

__attribute__((swift_name("ProgramProgramComponent")))
@protocol AppDiProgramProgramComponent
@required
- (void)applyStateListenerBlock:(void (^)(AppDiCoreMVUState *))block __attribute__((swift_name("applyStateListener(block:)")));
- (void)clear __attribute__((swift_name("clear()")));
- (void)clearStateListener __attribute__((swift_name("clearStateListener()")));
- (AppDiCoreMVUState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)start __attribute__((swift_name("start()")));
@end;

__attribute__((swift_name("ApiITimerComponent")))
@protocol AppDiApiITimerComponent <AppDiProgramProgramComponent>
@required
- (void)onStartClick __attribute__((swift_name("onStartClick()")));
- (void)onStopClick __attribute__((swift_name("onStopClick()")));
- (void)onTextChangedTxt:(NSString *)txt __attribute__((swift_name("onTextChanged(txt:)")));
- (void)onTimerValueChangedValue:(int32_t)value __attribute__((swift_name("onTimerValueChanged(value:)")));
@end;

__attribute__((swift_name("Kodein_diDIBindBuilder")))
@protocol AppDiKodein_diDIBindBuilder
@required
@property (readonly) AppDiKodein_typeTypeToken<id> *contextType __attribute__((swift_name("contextType")));
@end;

__attribute__((swift_name("Kodein_diDIBindBuilderWithContext")))
@protocol AppDiKodein_diDIBindBuilderWithContext <AppDiKodein_diDIBindBuilder>
@required
@end;

__attribute__((swift_name("Kodein_diDIBindBuilderWithScope")))
@protocol AppDiKodein_diDIBindBuilderWithScope <AppDiKodein_diDIBindBuilder>
@required
@property (readonly) id<AppDiKodein_diScope> scope __attribute__((swift_name("scope")));
@end;

__attribute__((swift_name("Kodein_diDIBuilder")))
@protocol AppDiKodein_diDIBuilder <AppDiKodein_diDIBindBuilderWithContext, AppDiKodein_diDIBindBuilderWithScope>
@required
- (id<AppDiKodein_diDIBuilderDirectBinder>)BindTag:(id _Nullable)tag overrides:(AppDiBoolean * _Nullable)overrides __attribute__((swift_name("Bind(tag:overrides:)")));
- (id<AppDiKodein_diDIBuilderTypeBinder>)BindType:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag overrides:(AppDiBoolean * _Nullable)overrides __attribute__((swift_name("Bind(type:tag:overrides:)")));
- (void)RegisterContextTranslatorTranslator:(id<AppDiKodein_diContextTranslator>)translator __attribute__((swift_name("RegisterContextTranslator(translator:)")));
- (id<AppDiKodein_diDIBuilderConstantBinder>)constantTag:(id)tag overrides:(AppDiBoolean * _Nullable)overrides __attribute__((swift_name("constant(tag:overrides:)")));
- (void)importModule:(AppDiKodein_diDIModule *)module allowOverride:(BOOL)allowOverride __attribute__((swift_name("import(module:allowOverride:)")));
- (void)importAllModules:(AppDiKotlinArray<AppDiKodein_diDIModule *> *)modules allowOverride:(BOOL)allowOverride __attribute__((swift_name("importAll(modules:allowOverride:)")));
- (void)importAllModules:(id)modules allowOverride_:(BOOL)allowOverride __attribute__((swift_name("importAll(modules:allowOverride_:)")));
- (void)importOnceModule:(AppDiKodein_diDIModule *)module allowOverride:(BOOL)allowOverride __attribute__((swift_name("importOnce(module:allowOverride:)")));
- (void)onReadyCb:(void (^)(id<AppDiKodein_diDirectDI>))cb __attribute__((swift_name("onReady(cb:)")));
@property (readonly) id<AppDiKodein_diDIContainerBuilder> containerBuilder __attribute__((swift_name("containerBuilder")));
@end;

__attribute__((swift_name("CoreMVUState")))
@interface AppDiCoreMVUState : AppDiBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("Kodein_diDIBuilderDirectBinder")))
@protocol AppDiKodein_diDIBuilderDirectBinder
@required
- (void)fromBinding:(id<AppDiKodein_diDIBinding>)binding __attribute__((swift_name("from(binding:)")));
@end;

__attribute__((swift_name("Kodein_diDIBuilderTypeBinder")))
@protocol AppDiKodein_diDIBuilderTypeBinder
@required
- (void)withBinding:(id<AppDiKodein_diDIBinding>)binding __attribute__((swift_name("with(binding:)")));
@end;

__attribute__((swift_name("Kodein_typeTypeToken")))
@interface AppDiKodein_typeTypeToken<T> : AppDiBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (AppDiKotlinArray<AppDiKodein_typeTypeToken<id> *> *)getGenericParameters __attribute__((swift_name("getGenericParameters()")));
- (AppDiKodein_typeTypeToken<T> * _Nullable)getRaw __attribute__((swift_name("getRaw()")));
- (NSArray<AppDiKodein_typeTypeToken<id> *> *)getSuper __attribute__((swift_name("getSuper()")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isAssignableFromTypeToken:(AppDiKodein_typeTypeToken<id> *)typeToken __attribute__((swift_name("isAssignableFrom(typeToken:)")));
- (BOOL)isGeneric __attribute__((swift_name("isGeneric()")));
- (BOOL)isWildcard __attribute__((swift_name("isWildcard()")));
- (NSString *)qualifiedDispString __attribute__((swift_name("qualifiedDispString()")));
- (NSString *)qualifiedErasedDispString __attribute__((swift_name("qualifiedErasedDispString()")));
- (NSString *)simpleDispString __attribute__((swift_name("simpleDispString()")));
- (NSString *)simpleErasedDispString __attribute__((swift_name("simpleErasedDispString()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((swift_name("Kodein_diContextTranslator")))
@protocol AppDiKodein_diContextTranslator
@required
- (id _Nullable)translateCtx:(id)ctx __attribute__((swift_name("translate(ctx:)")));
@property (readonly) AppDiKodein_typeTypeToken<id> *contextType __attribute__((swift_name("contextType")));
@property (readonly) AppDiKodein_typeTypeToken<id> *scopeType __attribute__((swift_name("scopeType")));
@end;

__attribute__((swift_name("Kodein_diDIBuilderConstantBinder")))
@protocol AppDiKodein_diDIBuilderConstantBinder
@required
- (void)WithValueType:(AppDiKodein_typeTypeToken<id> *)valueType value:(id)value __attribute__((swift_name("With(valueType:value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface AppDiKotlinArray<T> : AppDiBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(AppDiInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<AppDiKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((swift_name("Kodein_diDirectDIAware")))
@protocol AppDiKodein_diDirectDIAware
@required
@property (readonly) id<AppDiKodein_diDirectDI> directDI __attribute__((swift_name("directDI")));
@end;

__attribute__((swift_name("Kodein_diDirectDIBase")))
@protocol AppDiKodein_diDirectDIBase <AppDiKodein_diDirectDIAware>
@required
- (id (^)(id _Nullable))FactoryArgType:(AppDiKodein_typeTypeToken<id> *)argType type:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag __attribute__((swift_name("Factory(argType:type:tag:)")));
- (id (^ _Nullable)(id _Nullable))FactoryOrNullArgType:(AppDiKodein_typeTypeToken<id> *)argType type:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag __attribute__((swift_name("FactoryOrNull(argType:type:tag:)")));
- (id)InstanceType:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag __attribute__((swift_name("Instance(type:tag:)")));
- (id)InstanceArgType:(AppDiKodein_typeTypeToken<id> *)argType type:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag arg:(id _Nullable)arg __attribute__((swift_name("Instance(argType:type:tag:arg:)")));
- (id _Nullable)InstanceOrNullType:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag __attribute__((swift_name("InstanceOrNull(type:tag:)")));
- (id _Nullable)InstanceOrNullArgType:(AppDiKodein_typeTypeToken<id> *)argType type:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag arg:(id _Nullable)arg __attribute__((swift_name("InstanceOrNull(argType:type:tag:arg:)")));
- (id<AppDiKodein_diDirectDI>)OnContext:(AppDiKodein_diDIContext<id> *)context __attribute__((swift_name("On(context:)")));
- (id (^)(void))ProviderType:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag __attribute__((swift_name("Provider(type:tag:)")));
- (id (^)(void))ProviderArgType:(AppDiKodein_typeTypeToken<id> *)argType type:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag arg:(id _Nullable (^)(void))arg __attribute__((swift_name("Provider(argType:type:tag:arg:)")));
- (id (^ _Nullable)(void))ProviderOrNullType:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag __attribute__((swift_name("ProviderOrNull(type:tag:)")));
- (id (^ _Nullable)(void))ProviderOrNullArgType:(AppDiKodein_typeTypeToken<id> *)argType type:(AppDiKodein_typeTypeToken<id> *)type tag:(id _Nullable)tag arg:(id _Nullable (^)(void))arg __attribute__((swift_name("ProviderOrNull(argType:type:tag:arg:)")));
@property (readonly) id<AppDiKodein_diDIContainer> container __attribute__((swift_name("container")));
@property (readonly) id<AppDiKodein_diDI> di __attribute__((swift_name("di")));
@property (readonly) id<AppDiKodein_diDI> lazy __attribute__((swift_name("lazy")));
@end;

__attribute__((swift_name("Kodein_diDirectDI")))
@protocol AppDiKodein_diDirectDI <AppDiKodein_diDirectDIBase>
@required
@end;

__attribute__((swift_name("Kodein_diDIContainerBuilder")))
@protocol AppDiKodein_diDIContainerBuilder
@required
- (void)bindKey:(AppDiKodein_diDIKey<id, id, id> *)key binding:(id<AppDiKodein_diDIBinding>)binding fromModule:(NSString * _Nullable)fromModule overrides:(AppDiBoolean * _Nullable)overrides __attribute__((swift_name("bind(key:binding:fromModule:overrides:)")));
- (void)extendContainer:(id<AppDiKodein_diDIContainer>)container allowOverride:(BOOL)allowOverride copy:(NSSet<AppDiKodein_diDIKey<id, id, id> *> *)copy __attribute__((swift_name("extend(container:allowOverride:copy:)")));
- (void)onReadyCb:(void (^)(id<AppDiKodein_diDirectDI>))cb __attribute__((swift_name("onReady(cb:)")));
- (void)registerContextTranslatorTranslator:(id<AppDiKodein_diContextTranslator>)translator __attribute__((swift_name("registerContextTranslator(translator:)")));
- (id<AppDiKodein_diDIContainerBuilder>)subBuilderAllowOverride:(BOOL)allowOverride silentOverride:(BOOL)silentOverride __attribute__((swift_name("subBuilder(allowOverride:silentOverride:)")));
@end;

__attribute__((swift_name("Kodein_diScope")))
@protocol AppDiKodein_diScope
@required
- (AppDiKodein_diScopeRegistry *)getRegistryContext:(id _Nullable)context __attribute__((swift_name("getRegistry(context:)")));
@end;

__attribute__((swift_name("Kodein_diBinding")))
@protocol AppDiKodein_diBinding
@required
- (id (^)(id _Nullable))getFactoryDi:(id<AppDiKodein_diBindingDI>)di key:(AppDiKodein_diDIKey<id, id, id> *)key __attribute__((swift_name("getFactory(di:key:)")));
@end;

__attribute__((swift_name("Kodein_diDIBinding")))
@protocol AppDiKodein_diDIBinding <AppDiKodein_diBinding>
@required
- (NSString *)factoryFullName __attribute__((swift_name("factoryFullName()")));
- (NSString *)factoryName __attribute__((swift_name("factoryName()")));
@property (readonly) AppDiKodein_typeTypeToken<id> *argType __attribute__((swift_name("argType")));
@property (readonly) AppDiKodein_typeTypeToken<id> *contextType __attribute__((swift_name("contextType")));
@property (readonly) id<AppDiKodein_diDIBindingCopier> _Nullable copier __attribute__((swift_name("copier")));
@property (readonly) AppDiKodein_typeTypeToken<id> *createdType __attribute__((swift_name("createdType")));
@property (readonly) NSString *description_ __attribute__((swift_name("description_")));
@property (readonly) NSString *fullDescription __attribute__((swift_name("fullDescription")));
@property (readonly) id<AppDiKodein_diScope> _Nullable scope __attribute__((swift_name("scope")));
@property (readonly) BOOL supportSubTypes __attribute__((swift_name("supportSubTypes")));
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol AppDiKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

__attribute__((swift_name("Kodein_diDIContext")))
@interface AppDiKodein_diDIContext<C> : AppDiBase
@property (readonly) id<AppDiKodein_diReference> reference __attribute__((swift_name("reference")));
@property (readonly) AppDiKodein_typeTypeToken<C> *type __attribute__((swift_name("type")));
@end;

__attribute__((swift_name("Kodein_diDIContainer")))
@protocol AppDiKodein_diDIContainer
@required
- (NSArray<id (^)(id _Nullable)> *)allFactoriesKey:(AppDiKodein_diDIKey<id, id, id> *)key context:(AppDiKodein_diDIContext<id> *)context overrideLevel:(int32_t)overrideLevel __attribute__((swift_name("allFactories(key:context:overrideLevel:)")));
- (NSArray<id (^)(void)> *)allProvidersKey:(AppDiKodein_diDIKey<id, AppDiKotlinUnit *, id> *)key context:(AppDiKodein_diDIContext<id> *)context overrideLevel:(int32_t)overrideLevel __attribute__((swift_name("allProviders(key:context:overrideLevel:)")));
- (id (^)(id _Nullable))factoryKey:(AppDiKodein_diDIKey<id, id, id> *)key context:(AppDiKodein_diDIContext<id> *)context overrideLevel:(int32_t)overrideLevel __attribute__((swift_name("factory(key:context:overrideLevel:)")));
- (id (^ _Nullable)(id _Nullable))factoryOrNullKey:(AppDiKodein_diDIKey<id, id, id> *)key context:(AppDiKodein_diDIContext<id> *)context overrideLevel:(int32_t)overrideLevel __attribute__((swift_name("factoryOrNull(key:context:overrideLevel:)")));
- (id (^)(void))providerKey:(AppDiKodein_diDIKey<id, AppDiKotlinUnit *, id> *)key context:(AppDiKodein_diDIContext<id> *)context overrideLevel:(int32_t)overrideLevel __attribute__((swift_name("provider(key:context:overrideLevel:)")));
- (id (^ _Nullable)(void))providerOrNullKey:(AppDiKodein_diDIKey<id, AppDiKotlinUnit *, id> *)key context:(AppDiKodein_diDIContext<id> *)context overrideLevel:(int32_t)overrideLevel __attribute__((swift_name("providerOrNull(key:context:overrideLevel:)")));
@property (readonly) id<AppDiKodein_diDITree> tree __attribute__((swift_name("tree")));
@end;

__attribute__((swift_name("Kodein_diDIAware")))
@protocol AppDiKodein_diDIAware
@required
@property (readonly) id<AppDiKodein_diDI> di __attribute__((swift_name("di")));
@property (readonly) AppDiKodein_diDIContext<id> *diContext __attribute__((swift_name("diContext")));
@property (readonly) AppDiKodein_diDITrigger * _Nullable diTrigger __attribute__((swift_name("diTrigger")));
@end;

__attribute__((swift_name("Kodein_diDI")))
@protocol AppDiKodein_diDI <AppDiKodein_diDIAware>
@required
@property (readonly) id<AppDiKodein_diDIContainer> container __attribute__((swift_name("container")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kodein_diDIKey")))
@interface AppDiKodein_diDIKey<__contravariant C, __contravariant A, __covariant T> : AppDiBase
- (instancetype)initWithContextType:(AppDiKodein_typeTypeToken<C> *)contextType argType:(AppDiKodein_typeTypeToken<A> *)argType type:(AppDiKodein_typeTypeToken<T> *)type tag:(id _Nullable)tag __attribute__((swift_name("init(contextType:argType:type:tag:)"))) __attribute__((objc_designated_initializer));
- (AppDiKodein_typeTypeToken<C> *)component1 __attribute__((swift_name("component1()")));
- (AppDiKodein_typeTypeToken<A> *)component2 __attribute__((swift_name("component2()")));
- (AppDiKodein_typeTypeToken<T> *)component3 __attribute__((swift_name("component3()")));
- (id _Nullable)component4 __attribute__((swift_name("component4()")));
- (AppDiKodein_diDIKey<C, A, T> *)doCopyContextType:(AppDiKodein_typeTypeToken<C> *)contextType argType:(AppDiKodein_typeTypeToken<A> *)argType type:(AppDiKodein_typeTypeToken<T> *)type tag:(id _Nullable)tag __attribute__((swift_name("doCopy(contextType:argType:type:tag:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) AppDiKodein_typeTypeToken<A> *argType __attribute__((swift_name("argType")));
@property (readonly) NSString *bindDescription __attribute__((swift_name("bindDescription")));
@property (readonly) NSString *bindFullDescription __attribute__((swift_name("bindFullDescription")));
@property (readonly) AppDiKodein_typeTypeToken<C> *contextType __attribute__((swift_name("contextType")));
@property (readonly) NSString *description_ __attribute__((swift_name("description_")));
@property (readonly) NSString *fullDescription __attribute__((swift_name("fullDescription")));
@property (readonly) NSString *internalDescription __attribute__((swift_name("internalDescription")));
@property (readonly) id _Nullable tag __attribute__((swift_name("tag")));
@property (readonly) AppDiKodein_typeTypeToken<T> *type __attribute__((swift_name("type")));
@end;

__attribute__((swift_name("Kodein_diScopeCloseable")))
@protocol AppDiKodein_diScopeCloseable
@required
- (void)close __attribute__((swift_name("close()")));
@end;

__attribute__((swift_name("Kodein_diScopeRegistry")))
@interface AppDiKodein_diScopeRegistry : AppDiBase <AppDiKodein_diScopeCloseable>
- (void)clear __attribute__((swift_name("clear()")));
- (void)close __attribute__((swift_name("close()")));
- (id)getOrCreateKey:(id)key sync:(BOOL)sync creator:(AppDiKodein_diReferenceLocal<id, id<AppDiKodein_diReference>> *(^)(void))creator __attribute__((swift_name("getOrCreate(key:sync:creator:)")));
- (id<AppDiKodein_diReference> _Nullable)getOrNullKey:(id)key __attribute__((swift_name("getOrNull(key:)")));
- (void)removeKey:(id)key __attribute__((swift_name("remove(key:)")));
- (id)values __attribute__((swift_name("values()")));
@end;

__attribute__((swift_name("Kodein_diDIBindingCopier")))
@protocol AppDiKodein_diDIBindingCopier
@required
- (id<AppDiKodein_diDIBinding>)doCopyBuilder:(id<AppDiKodein_diDIContainerBuilder>)builder __attribute__((swift_name("doCopy(builder:)")));
@end;

__attribute__((swift_name("Kodein_diWithContext")))
@protocol AppDiKodein_diWithContext
@required
@property (readonly) id context __attribute__((swift_name("context")));
@end;

__attribute__((swift_name("Kodein_diBindingDI")))
@protocol AppDiKodein_diBindingDI <AppDiKodein_diDirectDI, AppDiKodein_diWithContext>
@required
- (id<AppDiKodein_diBindingDI>)noGlobalContext __attribute__((swift_name("noGlobalContext()")));
- (id (^)(id _Nullable))overriddenFactory __attribute__((swift_name("overriddenFactory()")));
- (id (^ _Nullable)(id _Nullable))overriddenFactoryOrNull __attribute__((swift_name("overriddenFactoryOrNull()")));
@end;

__attribute__((swift_name("Kodein_diReference")))
@protocol AppDiKodein_diReference
@required
- (id _Nullable)get __attribute__((swift_name("get()")));
@property (readonly) id<AppDiKodein_diReferenceMaker> maker __attribute__((swift_name("maker")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinUnit")))
@interface AppDiKotlinUnit : AppDiBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)unit __attribute__((swift_name("init()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((swift_name("Kodein_diDITree")))
@protocol AppDiKodein_diDITree
@required
- (NSArray<AppDiKotlinTriple<AppDiKodein_diDIKey<id, id, id> *, AppDiKodein_diDIDefinition<id, id, id> *, id<AppDiKodein_diContextTranslator>> *> *)findKey:(AppDiKodein_diDIKey<id, id, id> *)key overrideLevel:(int32_t)overrideLevel all:(BOOL)all __attribute__((swift_name("find(key:overrideLevel:all:)")));
- (NSArray<AppDiKotlinTriple<AppDiKodein_diDIKey<id, id, id> *, NSArray<AppDiKodein_diDIDefinition<id, id, id> *> *, id<AppDiKodein_diContextTranslator>> *> *)findSearch:(AppDiKodein_diSearchSpecs *)search __attribute__((swift_name("find(search:)")));
- (AppDiKotlinTriple<AppDiKodein_diDIKey<id, id, id> *, NSArray<AppDiKodein_diDIDefinition<id, id, id> *> *, id<AppDiKodein_diContextTranslator>> * _Nullable)getKey:(AppDiKodein_diDIKey<id, id, id> *)key __attribute__((swift_name("get(key:)")));
@property (readonly) NSDictionary<AppDiKodein_diDIKey<id, id, id> *, NSArray<AppDiKodein_diDIDefinition<id, id, id> *> *> *bindings __attribute__((swift_name("bindings")));
@property (readonly) NSArray<id<AppDiKodein_diExternalSource>> *externalSources __attribute__((swift_name("externalSources")));
@property (readonly) NSArray<id<AppDiKodein_diContextTranslator>> *registeredTranslators __attribute__((swift_name("registeredTranslators")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kodein_diDITrigger")))
@interface AppDiKodein_diDITrigger : AppDiBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)trigger __attribute__((swift_name("trigger()")));
@property (readonly) NSMutableArray<id<AppDiKotlinLazy>> *properties __attribute__((swift_name("properties")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kodein_diReferenceLocal")))
@interface AppDiKodein_diReferenceLocal<__covariant T, __covariant R> : AppDiBase
- (instancetype)initWithValue:(T)value reference:(R)reference __attribute__((swift_name("init(value:reference:)"))) __attribute__((objc_designated_initializer));
- (T)component1 __attribute__((swift_name("component1()")));
- (R)component2 __attribute__((swift_name("component2()")));
- (AppDiKodein_diReferenceLocal<T, R> *)doCopyValue:(T)value reference:(R)reference __attribute__((swift_name("doCopy(value:reference:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) R reference __attribute__((swift_name("reference")));
@property (readonly) T value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("Kodein_diReferenceMaker")))
@protocol AppDiKodein_diReferenceMaker
@required
- (AppDiKodein_diReferenceLocal<id, id<AppDiKodein_diReference>> *)makeCreator:(id (^)(void))creator __attribute__((swift_name("make(creator:)")));
@end;

__attribute__((swift_name("Kodein_diDIDefining")))
@interface AppDiKodein_diDIDefining<C, A, T> : AppDiBase
- (instancetype)initWithBinding:(id<AppDiKodein_diDIBinding>)binding fromModule:(NSString * _Nullable)fromModule __attribute__((swift_name("init(binding:fromModule:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id<AppDiKodein_diDIBinding> binding __attribute__((swift_name("binding")));
@property (readonly) NSString * _Nullable fromModule __attribute__((swift_name("fromModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kodein_diDIDefinition")))
@interface AppDiKodein_diDIDefinition<C, A, T> : AppDiKodein_diDIDefining<C, A, T>
- (instancetype)initWithBinding:(id<AppDiKodein_diDIBinding>)binding fromModule:(NSString * _Nullable)fromModule tree:(id<AppDiKodein_diDITree>)tree __attribute__((swift_name("init(binding:fromModule:tree:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithBinding:(id<AppDiKodein_diDIBinding>)binding fromModule:(NSString * _Nullable)fromModule __attribute__((swift_name("init(binding:fromModule:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (readonly) id<AppDiKodein_diDITree> tree __attribute__((swift_name("tree")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinTriple")))
@interface AppDiKotlinTriple<__covariant A, __covariant B, __covariant C> : AppDiBase
- (instancetype)initWithFirst:(A _Nullable)first second:(B _Nullable)second third:(C _Nullable)third __attribute__((swift_name("init(first:second:third:)"))) __attribute__((objc_designated_initializer));
- (A _Nullable)component1 __attribute__((swift_name("component1()")));
- (B _Nullable)component2 __attribute__((swift_name("component2()")));
- (C _Nullable)component3 __attribute__((swift_name("component3()")));
- (AppDiKotlinTriple<A, B, C> *)doCopyFirst:(A _Nullable)first second:(B _Nullable)second third:(C _Nullable)third __attribute__((swift_name("doCopy(first:second:third:)")));
- (BOOL)equalsOther:(id _Nullable)other __attribute__((swift_name("equals(other:)")));
- (int32_t)hashCode __attribute__((swift_name("hashCode()")));
- (NSString *)toString __attribute__((swift_name("toString()")));
@property (readonly) A _Nullable first __attribute__((swift_name("first")));
@property (readonly) B _Nullable second __attribute__((swift_name("second")));
@property (readonly) C _Nullable third __attribute__((swift_name("third")));
@end;

__attribute__((swift_name("Kodein_diSearchSpecs")))
@interface AppDiKodein_diSearchSpecs : AppDiBase
- (instancetype)initWithContextType:(AppDiKodein_typeTypeToken<id> * _Nullable)contextType argType:(AppDiKodein_typeTypeToken<id> * _Nullable)argType type:(AppDiKodein_typeTypeToken<id> * _Nullable)type tag:(id _Nullable)tag __attribute__((swift_name("init(contextType:argType:type:tag:)"))) __attribute__((objc_designated_initializer));
- (NSString *)description __attribute__((swift_name("description()")));
@property AppDiKodein_typeTypeToken<id> * _Nullable argType __attribute__((swift_name("argType")));
@property AppDiKodein_typeTypeToken<id> * _Nullable contextType __attribute__((swift_name("contextType")));
@property id _Nullable tag __attribute__((swift_name("tag")));
@property AppDiKodein_typeTypeToken<id> * _Nullable type __attribute__((swift_name("type")));
@end;

__attribute__((swift_name("Kodein_diExternalSource")))
@protocol AppDiKodein_diExternalSource
@required
- (id (^ _Nullable)(id _Nullable))getFactoryDi:(id<AppDiKodein_diBindingDI>)di key_:(AppDiKodein_diDIKey<id, id, id> *)key __attribute__((swift_name("getFactory(di:key_:)")));
@end;

__attribute__((swift_name("KotlinLazy")))
@protocol AppDiKotlinLazy
@required
- (BOOL)isInitialized __attribute__((swift_name("isInitialized()")));
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end;

#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
