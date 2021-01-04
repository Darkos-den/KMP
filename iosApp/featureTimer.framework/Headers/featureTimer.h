#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class FeatureTimerCoreMVUState, FeatureTimerCoreEffect, FeatureTimerCoreMessage, FeatureTimerCoreStateCmdData<T>, FeatureTimerTimerEffect, FeatureTimerTimerEffectTrigger, FeatureTimerTimerMessage, FeatureTimerTimerState, FeatureTimerKotlinThrowable, FeatureTimerKotlinArray<T>, FeatureTimerKotlinException, FeatureTimerKotlinRuntimeException, FeatureTimerKotlinIllegalStateException, FeatureTimerKotlinUnit;

@protocol FeatureTimerProgramProgramComponent, FeatureTimerKotlinx_coroutines_coreFlow, FeatureTimerCoreEffectHandler, FeatureTimerCoreReducer, FeatureTimerCoreScopedEffect, FeatureTimerCoreFlowEffect, FeatureTimerCoreFinalMessage, FeatureTimerKotlinx_coroutines_coreFlowCollector, FeatureTimerKotlinIterator;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

__attribute__((swift_name("KotlinBase")))
@interface FeatureTimerBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface FeatureTimerBase (FeatureTimerBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface FeatureTimerMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface FeatureTimerMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorFeatureTimerKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface FeatureTimerNumber : NSNumber
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
@interface FeatureTimerByte : FeatureTimerNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface FeatureTimerUByte : FeatureTimerNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface FeatureTimerShort : FeatureTimerNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface FeatureTimerUShort : FeatureTimerNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface FeatureTimerInt : FeatureTimerNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface FeatureTimerUInt : FeatureTimerNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface FeatureTimerLong : FeatureTimerNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface FeatureTimerULong : FeatureTimerNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface FeatureTimerFloat : FeatureTimerNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface FeatureTimerDouble : FeatureTimerNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface FeatureTimerBoolean : FeatureTimerNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((swift_name("ProgramProgramComponent")))
@protocol FeatureTimerProgramProgramComponent
@required
- (void)applyStateListenerBlock:(void (^)(FeatureTimerCoreMVUState *))block __attribute__((swift_name("applyStateListener(block:)")));
- (void)clear __attribute__((swift_name("clear()")));
- (void)clearStateListener __attribute__((swift_name("clearStateListener()")));
- (FeatureTimerCoreMVUState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)start __attribute__((swift_name("start()")));
@end;

__attribute__((swift_name("ITimerComponent")))
@protocol FeatureTimerITimerComponent <FeatureTimerProgramProgramComponent>
@required
- (void)onStartClick __attribute__((swift_name("onStartClick()")));
- (void)onStopClick __attribute__((swift_name("onStopClick()")));
- (void)onTextChangedTxt:(NSString *)txt __attribute__((swift_name("onTextChanged(txt:)")));
- (void)onTimerValueChangedValue:(int32_t)value __attribute__((swift_name("onTimerValueChanged(value:)")));
@end;

__attribute__((swift_name("CoreEffectHandler")))
@protocol FeatureTimerCoreEffectHandler
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)callEffect:(FeatureTimerCoreEffect *)effect completionHandler:(void (^)(FeatureTimerCoreMessage * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("call(effect:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)callAsFlowEffect:(FeatureTimerCoreEffect *)effect completionHandler:(void (^)(id<FeatureTimerKotlinx_coroutines_coreFlow> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("callAsFlow(effect:completionHandler:)")));
@end;

__attribute__((swift_name("ITimerEffectHandler")))
@protocol FeatureTimerITimerEffectHandler <FeatureTimerCoreEffectHandler>
@required
@end;

__attribute__((swift_name("CoreReducer")))
@protocol FeatureTimerCoreReducer
@required
- (FeatureTimerCoreStateCmdData<FeatureTimerCoreMVUState *> *)updateState:(FeatureTimerCoreMVUState *)state message:(FeatureTimerCoreMessage *)message __attribute__((swift_name("update(state:message:)")));
@end;

__attribute__((swift_name("ITimerReducer")))
@protocol FeatureTimerITimerReducer <FeatureTimerCoreReducer>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Tmp")))
@interface FeatureTimerTmp : FeatureTimerBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)tmp __attribute__((swift_name("tmp()")));
@end;

__attribute__((swift_name("CoreEffect")))
@interface FeatureTimerCoreEffect : FeatureTimerBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("TimerEffect")))
@interface FeatureTimerTimerEffect : FeatureTimerCoreEffect
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@end;

__attribute__((swift_name("CoreScopedEffect")))
@protocol FeatureTimerCoreScopedEffect
@required
@property (readonly) id scope __attribute__((swift_name("scope")));
@end;

__attribute__((swift_name("TimerEffect.Trigger")))
@interface FeatureTimerTimerEffectTrigger : FeatureTimerTimerEffect <FeatureTimerCoreScopedEffect>
@property (readonly) NSString *scope __attribute__((swift_name("scope")));
@end;

__attribute__((swift_name("CoreFlowEffect")))
@protocol FeatureTimerCoreFlowEffect
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimerEffect.TriggerStart")))
@interface FeatureTimerTimerEffectTriggerStart : FeatureTimerTimerEffectTrigger <FeatureTimerCoreFlowEffect>
- (instancetype)initWithValue:(int32_t)value __attribute__((swift_name("init(value:)"))) __attribute__((objc_designated_initializer));
@property (readonly) int32_t value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimerEffect.TriggerStop")))
@interface FeatureTimerTimerEffectTriggerStop : FeatureTimerTimerEffectTrigger
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)stop __attribute__((swift_name("init()")));
@end;

__attribute__((swift_name("CoreMessage")))
@interface FeatureTimerCoreMessage : FeatureTimerBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("TimerMessage")))
@interface FeatureTimerTimerMessage : FeatureTimerCoreMessage
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@end;

__attribute__((swift_name("CoreFinalMessage")))
@protocol FeatureTimerCoreFinalMessage
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimerMessage.Finish")))
@interface FeatureTimerTimerMessageFinish : FeatureTimerTimerMessage <FeatureTimerCoreFinalMessage>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)finish __attribute__((swift_name("init()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimerMessage.StartClick")))
@interface FeatureTimerTimerMessageStartClick : FeatureTimerTimerMessage
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)startClick __attribute__((swift_name("init()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimerMessage.StopClick")))
@interface FeatureTimerTimerMessageStopClick : FeatureTimerTimerMessage
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)stopClick __attribute__((swift_name("init()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimerMessage.TextChanged")))
@interface FeatureTimerTimerMessageTextChanged : FeatureTimerTimerMessage
- (instancetype)initWithValue:(NSString *)value __attribute__((swift_name("init(value:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimerMessage.ValueChanged")))
@interface FeatureTimerTimerMessageValueChanged : FeatureTimerTimerMessage
- (instancetype)initWithNewValue:(int32_t)newValue __attribute__((swift_name("init(newValue:)"))) __attribute__((objc_designated_initializer));
@property (readonly, getter=doNewValue) int32_t newValue __attribute__((swift_name("newValue")));
@end;

__attribute__((swift_name("CoreMVUState")))
@interface FeatureTimerCoreMVUState : FeatureTimerBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimerState")))
@interface FeatureTimerTimerState : FeatureTimerCoreMVUState
- (instancetype)initWithCount:(int32_t)count value:(int32_t)value progress:(BOOL)progress str:(NSString *)str __attribute__((swift_name("init(count:value:progress:str:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (FeatureTimerTimerState *)doCopyCount:(int32_t)count value:(int32_t)value progress:(BOOL)progress str:(NSString *)str __attribute__((swift_name("doCopy(count:value:progress:str:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t count __attribute__((swift_name("count")));
@property (readonly) BOOL progress __attribute__((swift_name("progress")));
@property (readonly) NSString *str __attribute__((swift_name("str")));
@property (readonly) int32_t value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("KotlinThrowable")))
@interface FeatureTimerKotlinThrowable : FeatureTimerBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(FeatureTimerKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(FeatureTimerKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (FeatureTimerKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) FeatureTimerKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
@end;

__attribute__((swift_name("KotlinException")))
@interface FeatureTimerKotlinException : FeatureTimerKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(FeatureTimerKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(FeatureTimerKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinRuntimeException")))
@interface FeatureTimerKotlinRuntimeException : FeatureTimerKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(FeatureTimerKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(FeatureTimerKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinIllegalStateException")))
@interface FeatureTimerKotlinIllegalStateException : FeatureTimerKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(FeatureTimerKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(FeatureTimerKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinCancellationException")))
@interface FeatureTimerKotlinCancellationException : FeatureTimerKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(FeatureTimerKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(FeatureTimerKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreFlow")))
@protocol FeatureTimerKotlinx_coroutines_coreFlow
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)collectCollector:(id<FeatureTimerKotlinx_coroutines_coreFlowCollector>)collector completionHandler:(void (^)(FeatureTimerKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("collect(collector:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CoreStateCmdData")))
@interface FeatureTimerCoreStateCmdData<T> : FeatureTimerBase
- (instancetype)initWithState:(T)state effect:(FeatureTimerCoreEffect *)effect __attribute__((swift_name("init(state:effect:)"))) __attribute__((objc_designated_initializer));
- (T)component1 __attribute__((swift_name("component1()")));
- (FeatureTimerCoreEffect *)component2 __attribute__((swift_name("component2()")));
- (FeatureTimerCoreStateCmdData<T> *)doCopyState:(T)state effect:(FeatureTimerCoreEffect *)effect __attribute__((swift_name("doCopy(state:effect:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) FeatureTimerCoreEffect *effect __attribute__((swift_name("effect")));
@property (readonly) T state __attribute__((swift_name("state")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface FeatureTimerKotlinArray<T> : FeatureTimerBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(FeatureTimerInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<FeatureTimerKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreFlowCollector")))
@protocol FeatureTimerKotlinx_coroutines_coreFlowCollector
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)emitValue:(id _Nullable)value completionHandler:(void (^)(FeatureTimerKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("emit(value:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinUnit")))
@interface FeatureTimerKotlinUnit : FeatureTimerBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)unit __attribute__((swift_name("init()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol FeatureTimerKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
